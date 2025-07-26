package com.tpch.query5.config;

import java.time.LocalDate;

public class AppConfig {
    public String regionName;
    public LocalDate startDate;
    public LocalDate endDate;
    public int threads = 1;
    public String tablePath;
    public String resultPath;

    public static AppConfig fromArgs(String[] args) {
        AppConfig config = new AppConfig();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--r_name":
                    config.regionName = args[++i];
                    break;
                case "--start_date":
                    config.startDate = LocalDate.parse(args[++i]);
                    break;
                case "--end_date":
                    config.endDate = LocalDate.parse(args[++i]);
                    break;
                case "--threads":
                    config.threads = Integer.parseInt(args[++i]);
                    break;
                case "--table_path":
                    config.tablePath = args[++i];
                    break;
                case "--result_path":
                    config.resultPath = args[++i];
                    break;
                default:
                    System.err.println("Unknown argument: " + args[i]);
            }
        }
        return config;
    }
}
