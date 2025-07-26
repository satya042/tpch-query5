package com.tpch.query5.model;

public class Supplier {
    private int suppKey;
    private int nationKey;

    public Supplier(int suppKey, int nationKey) {
        this.suppKey = suppKey;
        this.nationKey = nationKey;
    }

    public int getSuppKey() {
        return suppKey;
    }

    public int getNationKey() {
        return nationKey;
    }
}
