package com.ohgiraffers.transactional.section01.annotation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private int orderCode;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;
    /*insert 의 편리함을 위해 Order의 개념인 OrderMenu엔티티까ㅣㅈ 포함해서 필드를 작성한다(insert 시 추가)*/
    private List<OrderMenu> orderMenus;

    public Order() {}

    public Order(int orderCode, String orderDate, String orderTime, int totalOrderPrice) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalOrderPrice = totalOrderPrice;
    }

    public Order(LocalDate orderDate, LocalTime orderTime, int totalOrderPrice, List<OrderMenu> orderMenus1) {
//        this.orderDate= String.valueOf(orderDate);
//        this.orderTime= String.valueOf(orderTime);
        /*localDate, localTime형을 디비에 저장하고 싶은 문자열 형태로 변환하는 작업 응용해보기
            * 해당 날은 오른 쪽 형식에 맞춰 작성시키도록 한 것이다!!
        * */
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.orderTime= orderTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.totalOrderPrice=totalOrderPrice;
        this.orderMenus=orderMenus1;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                '}';
    }
}
