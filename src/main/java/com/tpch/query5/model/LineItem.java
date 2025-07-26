package com.tpch.query5.model;

public class LineItem {
    private int orderKey;
    private int suppKey;
    private double extendedPrice;
    private double discount;

    public LineItem(int orderKey, int suppKey, double extendedPrice, double discount) {
        this.orderKey = orderKey;
        this.suppKey = suppKey;
        this.extendedPrice = extendedPrice;
        this.discount = discount;
    }

    public int getOrderKey() {
        return orderKey;
    }

    public int getSuppKey() {
        return suppKey;
    }

    public double getExtendedPrice() {
        return extendedPrice;
    }

    public double getDiscount() {
        return discount;
    }
}
