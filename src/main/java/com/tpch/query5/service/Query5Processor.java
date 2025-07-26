package com.tpch.query5.service;

import com.tpch.query5.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Query5Processor {
    public static Map<String, Double> execute(
            List<Customer> customers,
            List<Order> orders,
            List<LineItem> lineItems,
            List<Supplier> suppliers,
            List<Nation> nations,
            List<Region> regions,
            String regionName,
            LocalDate startDate,
            LocalDate endDate
    ) {
        Set<Integer> regionKeys = regions.stream()
                .filter(r -> r.getName().equals(regionName))
                .map(Region::getRegionKey)
                .collect(Collectors.toSet());

        Set<Integer> regionNationKeys = nations.stream()
                .filter(n -> regionKeys.contains(n.getRegionKey()))
                .map(Nation::getNationKey)
                .collect(Collectors.toSet());

        Set<Integer> customerKeys = customers.parallelStream()
                .filter(c -> regionNationKeys.contains(c.getNationKey()))
                .map(Customer::getCustKey)
                .collect(Collectors.toSet());

        Set<Integer> orderKeys = orders.parallelStream()
                .filter(o -> customerKeys.contains(o.getCustKey()) &&
                        !o.getOrderDate().isBefore(startDate) &&
                        o.getOrderDate().isBefore(endDate)) // "< endDate" per SQL
                .map(Order::getOrderKey)
                .collect(Collectors.toSet());

        // Step 5: Map supplierKey -> nationKey (all suppliers)
        Map<Integer, Integer> supplierToNation = suppliers.parallelStream()
                .collect(Collectors.toMap(Supplier::getSuppKey, Supplier::getNationKey));

        // Step 6: Map customerKey -> nationKey
        Map<Integer, Integer> customerToNation = customers.parallelStream()
                .collect(Collectors.toMap(Customer::getCustKey, Customer::getNationKey));

        // Step 7: Map nationKey -> nationName
        Map<Integer, String> nationKeyToName = nations.stream()
                .collect(Collectors.toMap(Nation::getNationKey, Nation::getName));

        // Step 8: Build custKey -> orderKeys map for filtering
        Map<Integer, Integer> orderToCustomer = orders.stream()
                .filter(o -> customerKeys.contains(o.getCustKey()))
                .collect(Collectors.toMap(Order::getOrderKey, Order::getCustKey));

        // Step 9: Calculate revenue by nation (where c_nationkey == s_nationkey)
        Map<String, Double> revenueByNation = new ConcurrentHashMap<>();

        lineItems.parallelStream()
                .filter(l -> orderKeys.contains(l.getOrderKey()))
                .forEach(l -> {
                    int suppKey = l.getSuppKey();
                    int orderKey = l.getOrderKey();
                    int custKey = orderToCustomer.getOrDefault(orderKey, -1);
                    int suppNation = supplierToNation.getOrDefault(suppKey, -1);
                    int custNation = customerToNation.getOrDefault(custKey, -2);

                    // Match c_nationkey == s_nationkey
                    if (suppNation == custNation) {
                        String nationName = nationKeyToName.getOrDefault(suppNation, "UNKNOWN");
                        double revenue = l.getExtendedPrice() * (1 - l.getDiscount());

                        revenueByNation.merge(nationName, revenue, Double::sum);
                    }
                });

        return revenueByNation.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}