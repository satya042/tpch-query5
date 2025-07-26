package com.tpch.query5.model;

public class Customer {
    private int custKey;
    private int nationKey;

    public Customer(int custKey, int nationKey) {
        this.custKey = custKey;
        this.nationKey = nationKey;
    }

    public int getCustKey() {
        return custKey;
    }

    public int getNationKey() {
        return nationKey;
    }
}
