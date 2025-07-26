package com.tpch.query5.model;

public class Nation {
    private int nationKey;
    private String name;
    private int regionKey;

    public Nation(int nationKey, String name, int regionKey) {
        this.nationKey = nationKey;
        this.name = name;
        this.regionKey = regionKey;
    }

    public int getNationKey() {
        return nationKey;
    }

    public String getName() {
        return name;
    }

    public int getRegionKey() {
        return regionKey;
    }
}
