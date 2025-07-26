package com.tpch.query5.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ResultWriter {
    public static void writeResults(String resultPath, Map<String, Double> results) throws IOException {
        try (FileWriter writer = new FileWriter(resultPath)) {
            writer.write("Nation,Revenue\n");
            for (Map.Entry<String, Double> entry : results.entrySet()) {
                writer.write(entry.getKey() + "," + String.format("%.2f", entry.getValue()) + "\n");
            }
        }
    }
}
