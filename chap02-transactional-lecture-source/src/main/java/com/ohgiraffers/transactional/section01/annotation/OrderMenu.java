package com.ohgiraffers.transactional.section01.annotation;

public class OrderMenu {
    private int menuCode;
    private int orderCode;
    private int orderamount;

    public OrderMenu() {}

    public OrderMenu(int menuCode, int orderCode, int orderamount) {
        this.menuCode = menuCode;
        this.orderCode = orderCode;
        this.orderamount = orderamount;
    }

    public OrderMenu(int menuCode, int orderamount) {
        this.menuCode = menuCode;
        this.orderamount = orderamount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public int getOrderamount() {
        return orderamount;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "menuCode=" + menuCode +
                ", orderCode=" + orderCode +
                ", orderamount=" + orderamount +
                '}';
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }
}
