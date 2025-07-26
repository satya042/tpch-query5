package com.tpch.query5.model;

import java.time.LocalDate;

public class Order {
    private int orderKey;
    private int custKey;
    private LocalDate orderDate;

    public Order(int orderKey, int custKey, LocalDate orderDate) {
        this.orderKey = orderKey;
        this.custKey = custKey;
        this.orderDate = orderDate;
    }

    public int getOrderKey() {
        return orderKey;
    }

    public int getCustKey() {
        return custKey;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

}
