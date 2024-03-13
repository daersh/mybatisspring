package com.ohgiraffers.transactional.section01.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderMapper orderMapper;
    private MenuMapper menuMapper;

    /**
     * <h2>OrderDTO</h2>
     * 서비스 계층부터 개발할 때는 사용자의 입력 값들이 어떻게 DTO 또는 Map으로 묶여서 서비스로 넘어올 지 고민하여 작성하고 개발한다.
     * 현재의 경우 사용자가 고른 메뉴들 각각의 코드 번호와 고른 메뉴 갯수, 그리고 서버의 현재 시간이 담긴 채 넘어왔다고 생각하고 개발하자.
     * */
    @Autowired
    public OrderService(OrderMapper orderMapper, MenuMapper menuMapper) {
        this.orderMapper = orderMapper;
        this.menuMapper = menuMapper;
    }

    /** <h2>Transactional</h2>
     * <li>서비스 메소드 내부에서 모든 DML 관련 작업에 예외없이 잘 동작하면 commit, 예외 발생 시 rollback 적용된다.</li>
     * <br><br>
     *      <h2>전파행위 옵션</h2>
     *      <ul>
     *      1. REQUIRED: <ul>진행 중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션에서 실행하되 그렇지 않은 경우 새 트랜잭션을 시작해서 실행한다.</ul>
     *      2. REQUIRED_NEW: <ul>항상 새 트랜잭션을 시작해 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단시킨다.</ul>
     *      3. SUPPORTS: <ul>진행중인 트랜잭션이 있으면 현재 메소드를 그 트랜잭션 내에서 실행하되, 그렇지 않을 경우 트랜잭션 없이 실행한다.</ul>
     *      4. NOT_SUPPORTED: <ul>트랜잭션 없이 현재 메소드를 실행하고 진행중인 트랜잭션이 있으면 잠시 중단한다</ul>
     *      5. MANDATORY: <ul>반드시 트랜잭션을 걸고 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.</ul>
     *      6. NEVER: <ul>반드시 트랜잭션 없이 현재 메소드를 실행하되 진행중인 트랜잭션이 있으면 예외를 던진다.</ul>
     *      7. NESTED: <ul>진행중인 트랜잭션이 있으면 현재 메소드를 이 트랜잭션의 중첩트랜잭션 내에서 실행한다. 진행중인 트랜잭션이 없으면 새 트랜잭션을
     *                실행한다.<br>
     *                배치 실행 도중 처리 할 업무가 백만개라고 하면 10만개씩 끊어서 커밋하는 경우 중간에 잘못 되어도 중첩 트랜잭션을 롤백하면 전체가
     *                아닌 10만개만 롤백된다.<br>
     *                세이브포인트를 이용하는 방식이다. 따라서 세이브포인트를 지원하지 않는 경우 사용 불가능하다.</ul>
     *        </ul>
             <h2>격리레벨 (동시성)</h2>
    <ul>
     *      1. DEFAULT: <ul>DB의 기본 격리 수준을 사용한다. 대다수는 READ_COMMITTED가 기본 격리 수준이다.</ul>
     *      2. READ_UNCOMMITTED: <ul>다른 트랜젝션이 아직 커밋하지 않은 값을 다른 트랜젝션이 읽을 수 있다.(모든 동시성 문제 발생)<br>
     *      *                    따라서 오염된 값을 읽거나, 재현 불가능한 값 읽기, 허상 읽기 등의 문제가 발생할 수 있다.</ul>
     *      3. READ_COMMITTED: <ul>트랜젝션이 다른 트랜젝션에서 커밋한 값만 읽을 수 있다.<br>
     *      *                  오염된 값 읽기 문제는 해결되지만 재현 불가능한 값 읽기 및 허상읽기는 여전히 발생할 수 있다.(다른 로우 수정 및 추가는 막을 수 없다.)<br></ul>
     *      4. REPEATABLE_READ: <ul>트랜젝션이 어떤 필드를 여러 번 읽어도 동일한 값을 읽도록 보장한다.<br>
     *      *                   트랜젝션이 지속되는 동안에는 다른 트랜젝션이 해당 필드를 변경할 수 없다.<br>
     *      *                   오염된 값 읽기, 재현 불가능한 값 읽기는 해결되지만 허상읽기는 여전히 발생할 수 있다.(다른 로우 추가는 막을 수 없다.)<br></ul>
     *      5. SERIALIZABLE: <ul>트랜젝션이 테이블을 여러 번 읽어도 정확히 동일한 로우를 읽도록 보장한다. 트랜젝션이 지속되는 동안에는
     *      *                다른 트랜젝션이 해당 테이블에 삽입, 수정, 삭제를 할 수 없다.<br>
     *      *                동시성 문제는 모두 해소되지만 성능은 현저하게 떨어진다.(모든 동시성 문제는 막을 수 있다.)<br></ul>
     *      *
     *      6. 오염된값: <ul>하나의 트랜젝션이 데이터를 변경 후 잠시 기다리는 동안 다른 트랜젝션이 데이터를 읽게 되면,
     *      *          격리레벨이 READ_UNCOMMITTED인 경우 아직 변경 후 커밋하지 않은 재고값을 그대로 읽게 된다.<br>
     *      *          그러나 처음 트랜젝션이 데이터를 롤백하게 되면 다른 트랜젝션이 읽은 값은 더 이상 유효하지 않은 일시적인 값이 된다.<br>
     *      *          이것을 오염된 값라고 한다.<br></ul>
     *      7. 재현불가능한값읽기: <ul>처음 트랜젝션이 데이터를 수정하면 수정이 되고 아직 커밋되지 않은 로우에 수정 잠금을 걸어둔 상태에다.<br>
     *      *                      결국 다른 트랜젝션은 이 트랜젝션이 커밋 혹은 롤백 되고 수정잠금이 풀릴 때까지 기다렸다가 읽을 수 밖에 없게 된다.<br>
     *      *                      하지만 다른 로우에 대해서는 또 다른 트랜젝션이 데이터를 수정하고 커밋을 하게 되면
     *      *                      가장 처음 동작한 트랜젝션이 데이터를 커밋하고 다시 조회를 한 경우 처음 읽은 그 값이 아니게 된다.<br>
     *      *                      이것이 재현 불가능한 값이라고 한다.<br></ul>
     *      8. 허상읽기: <ul>처음 트랜젝션이 테이블에서 여러 로우를 읽은 후 이후 트랜젝션이 같은 테이블의 로우를 추가하는 경우
     *      *            처음 트랜젝션이 같은 테이블을 다시 읽으면 자신이 처음 읽었을 때와 달리 새로 추가 된 로우가 있을 것이다.<br>
     *      *            이것을 허상 읽기라고 한다. (재현 불가능한 값 읽기와 유사하지만 허상 읽기는 여러 로우가 추가되는 경우를 말한다.)</ul>
     *      </ul>
     * */
    @Transactional
    public void registNewOrder(OrderDTO orderInfo){
        /*1. 주문한 메뉴 코드 추출*/
        /*1-1. stream을 통한 방법*/
        List<Integer> menuCode = orderInfo.getOrderMenus().stream().map(OrderMenuDTO::getMenuCode).collect(Collectors.toList());

        /*1-2. foreach를 통한 방법*/
        List<Integer> menuCode2 = new ArrayList<>();
        List<OrderMenuDTO> orderMenus = orderInfo.getOrderMenus();
        for(OrderMenuDTO orderMenuDTO: orderMenus){
            menuCode2.add(orderMenuDTO.getMenuCode());
        }
        System.out.println("menuCode = " + menuCode);
        System.out.println("menuCode2 = " + menuCode2);

        /*2. 주문한 메뉴 별로 Menu 엔티티에 담아서 조회(select)하기(부가적인 메뉴의 정보 추출)*/
        System.out.println("2. 주문한 메뉴 별로 Menu 엔티티에 담아서 조회");
        Map<String,List<Integer> > map = new HashMap<>();
        map.put("menuCodes",menuCode);
        List<Menu> menus= menuMapper.selectMenuByMenuCodes(map);
        menus.forEach(System.out::println);

        /*3. 주문건에 대한 주문 총 합계 계산)*/
        int totalOrderPrice = calcTotalOrderPrice(orderInfo.getOrderMenus(), menus);
        System.out.println("3. 주문건에 대한 주문 총 합계 계산\ntotalOrderPrice = " + totalOrderPrice);

        /*4. 1부터 3까지를 하면 tbl_order 테이블에 삽입(insert) 가능하다*/
        System.out.println("4. order insert");
        /*4-1 insert 하기 위해 테이블과 매칭되는 엔티티로 옮겨 담는다.*/
        /*  방법 1.*/
        List<OrderMenu> orderMenus1 = new ArrayList<>(
                orderInfo.getOrderMenus().stream().map(dto-> {
                    return new OrderMenu(dto.getMenuCode(),dto.getOrderAmount());
                }).collect(Collectors.toList())
        );
        /*  방법 2.*/
        List<OrderMenu> orderMenus2 = new ArrayList<>();
        List<OrderMenuDTO> orderMenuDTO = orderInfo.getOrderMenus();
        for(OrderMenuDTO menuDTO: orderMenuDTO)
            orderMenus2.add(new OrderMenu(menuDTO.getMenuCode(),menuDTO.getOrderAmount()));
        Order order = new Order(orderInfo.getOrderDate(),orderInfo.getOrderTime(),totalOrderPrice,orderMenus1);
        System.out.println("order = " + order);

        /*tbl_order 테이블에 삽입*/
        orderMapper.registOrder(order);
        System.out.println("insert 후 order");

        /*5. tbl_order_menu에도 주문한 메뉴 갯수만큼 추가(insert)한다.*/
        int orderMenuSize = orderMenus2.size();
        for (int i = 0; i < orderMenuSize; i++) {
            OrderMenu orderMenu = orderMenus2.get(i);
            orderMenu.setOrderCode(order.getOrderCode());   //설명. 필요시 setter 사용

            orderMapper.registOrderMenu(orderMenu);
        }
    }

    /*주문 총 합 구하기 (orderMenus: 메뉴코드,갯수    menus: 메뉴코드, 가격)*/
    private int calcTotalOrderPrice(List<OrderMenuDTO> orderMenus, List<Menu> menus) {
        int totalOrderPrice = 0;
        int orderMenuSize = orderMenus.size();
        for (int i = 0; i < orderMenuSize; i++) {
            OrderMenuDTO orderMenuDTO = orderMenus.get(i);
            Menu menu = menus.get(i);
            totalOrderPrice+= menu.getMenuPrice()*orderMenuDTO.getOrderAmount();
        }
        return totalOrderPrice;
    }

}
