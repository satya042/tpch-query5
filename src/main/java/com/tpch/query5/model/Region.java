package com.tpch.query5.model;

public class Region {
    private int regionKey;
    private String name;

    public Region(int regionKey, String name) {
        this.regionKey = regionKey;
        this.name = name;
    }

    public int getRegionKey() {
        return regionKey;
    }

    public String getName() {
        return name;
    }
}
