package com.ohgiraffers.transactional.section01.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
/*DML(insert,del,update) 작업 테스트 시 실제 db 적용 안하려면 Transactional 어노테이션 꼭 추가해야함!!!!!!!!*/
@Transactional /*테스트니 커밋 날리지 말라는 뜻, 테스트코드가 아닌 곳에 쓰이면 다른 목적으로 쓰이니 찾아볼것~~!~@@!@~@!!@~!@~!@~!@~!@ */
class OrderServiceTest {
    @Autowired
    private OrderService registOrderService;

    private static Stream<Arguments> getOrderInfo(){
        OrderDTO orderInfo = new OrderDTO();
        orderInfo.setOrderDate(LocalDate.now());
        orderInfo.setOrderTime(LocalTime.now());
        orderInfo.setOrderMenus(
                List.of(
                        new OrderMenuDTO(1,10),
                        new OrderMenuDTO(2,11)
                )
        );

        return Stream.of(Arguments.of(orderInfo));
    }

    @DisplayName("주문등록test")
    @ParameterizedTest
    @MethodSource("getOrderInfo")
    void testRegistNewOrder(OrderDTO orderInfo){
        Assertions.assertDoesNotThrow(
                ()->registOrderService.registNewOrder(orderInfo)
        );

    }


}