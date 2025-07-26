package com.tpch.query5.loader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tpch.query5.model.Region;

public class RegionLoader implements DataLoader<Region> {
    @Override
    public List<Region> load(String filePath) throws IOException {
        List<Region> regions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\|");
                regions.add(new Region(Integer.parseInt(tokens[0]),tokens[1]));
            }
        }
        return regions;
    }
}
