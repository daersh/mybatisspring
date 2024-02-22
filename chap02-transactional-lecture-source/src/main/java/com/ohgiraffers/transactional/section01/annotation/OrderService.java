package com.ohgiraffers.transactional.section01.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderMapper orderMapper;
    private MenuMapper menuMapper;


    @Autowired
    public OrderService(OrderMapper orderMapper, MenuMapper menuMapper) {
        this.orderMapper = orderMapper;
        this.menuMapper = menuMapper;
    }

    /**
    * <h2>OrderDTO</h2>
     * 서비스 계층부터 개발할 때는 사용자의 입력 값들이 어떻게 DTO 또는 Map으로 묶여서 서비스로 넘어올 지 고민하여 작성하고 개발한다.
     * 현재의 경우 사용자가 고른 메뉴들 각각의 코드 번호와 고른 메뉴 갯수, 그리고 서버의 현재 시간이 담긴 채 넘어왔다고 생각하고 개발하자.
    * */
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

        Map<String,List<Integer> > map = new HashMap<>();
        map.put("menuCodes",menuCode);
        List<Menu> menus= menuMapper.selectMenuByMenuCodes(map);
        menus.forEach(System.out::println);

        /*2. 주문한 메뉴 별로 Menu 엔티티에 담아서 조회(select)하기(부가적인 메뉴의 정보 추출)*/

        /*3. 주문건에 대한 주문 총 합계 계산*/

        /*4. 1부터 3까지를 하면 tbl_order 테이블에 삽입(insert) 가능하다*/

        /*5. tbl_order_menu에도 주문한 메뉴 갯수만큼 추가(insert)한다.*/

    }
}
