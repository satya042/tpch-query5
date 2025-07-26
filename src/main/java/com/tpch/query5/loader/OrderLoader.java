package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.Order;

public class OrderLoader implements DataLoader<Order> {
    @Override
    public List<Order> load(String filePath) throws IOException {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                orders.add(new Order(
                    Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]),
                    LocalDate.parse(tokens[4])
                ));
            }
        }
        return orders;
    }
}
