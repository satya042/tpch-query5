package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.Nation;

public class NationLoader implements DataLoader<Nation> {
    @Override
    public List<Nation> load(String filePath) throws IOException {
        List<Nation> nations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                nations.add(new Nation(
                    Integer.parseInt(tokens[0]),
                    tokens[1],
                    Integer.parseInt(tokens[2])
                ));
            }
        }
        return nations;
    }
}
