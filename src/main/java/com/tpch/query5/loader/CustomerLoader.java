package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.Customer;

public class CustomerLoader implements DataLoader<Customer> {
    @Override
    public List<Customer> load(String filePath) throws IOException {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                customers.add(new Customer(
                        Integer.parseInt(tokens[0]),
                        Integer.parseInt(tokens[3])
                ));
            }
        }
        return customers;
    }
}