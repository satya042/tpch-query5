package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.Supplier;

public class SupplierLoader implements DataLoader<Supplier> {
    @Override
    public List<Supplier> load(String filePath) throws IOException {
        List<Supplier> suppliers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                suppliers.add(new Supplier(
                    Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[3])
                ));
            }
        }
        return suppliers;
    }
}
