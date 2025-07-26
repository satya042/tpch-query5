package com.tpch.query5.util;

import com.tpch.query5.loader.*;
import java.util.*;

public class TpchDataLoader {
    private static final Map<String, DataLoader<?>> registry = new HashMap<>();
    static {
        registry.put("customer", new CustomerLoader());
        registry.put("orders", new OrderLoader());
        registry.put("lineItem", new LineItemLoader());
        registry.put("supplier", new SupplierLoader());
        registry.put("nation", new NationLoader());
        registry.put("region", new RegionLoader());
    }

    @SuppressWarnings("unchecked")
    public static <T> DataLoader<T> getLoader(String name) {
        return (DataLoader<T>) registry.get(name);
    }
}
