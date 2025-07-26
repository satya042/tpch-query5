package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.LineItem;

public class LineItemLoader implements DataLoader<LineItem> {
    @Override
    public List<LineItem> load(String filePath) throws IOException {
        List<LineItem> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                items.add(new LineItem(
                    Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[2]),
                    Double.parseDouble(tokens[5]),
                    Double.parseDouble(tokens[6])
                ));
            }
        }
        return items;
    }
}
