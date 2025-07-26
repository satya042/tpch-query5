package com.tpch.query5;

import com.tpch.query5.config.AppConfig;
import com.tpch.query5.util.ResultWriter;
import com.tpch.query5.util.TpchDataLoader;
import com.tpch.query5.model.*;
import com.tpch.query5. service.Query5Processor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.*;

public class TpchQuery5App
{
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        if (args.length < 5) {
            System.out.println("Usage: <data_path> <region_name> <start_date> <end_date> <result_path>");
            System.exit(1);
        }
        AppConfig config = AppConfig.fromArgs(args);
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(Math.max(1, config.threads));

        System.out.println("Loading data...");
        Future<List<Customer>> customersFuture = executor.submit(() ->
                TpchDataLoader.<Customer>getLoader("customer").load(Paths.get(config.tablePath, "customer.tbl").toString()));

        Future<List<Order>> ordersFuture = executor.submit(() ->
            TpchDataLoader.<Order>getLoader("orders").load(Paths.get(config.tablePath, "orders.tbl").toString()));

        Future<List<LineItem>> lineItemsFuture = executor.submit(() ->
            TpchDataLoader.<LineItem>getLoader("lineItem").load(Paths.get(config.tablePath, "lineitem.tbl").toString()));

        Future<List<Supplier>> suppliersFuture = executor.submit(() ->
            TpchDataLoader.<Supplier>getLoader("supplier").load(Paths.get(config.tablePath, "supplier.tbl").toString()));

        Future<List<Nation>> nationsFuture = executor.submit(() ->
            TpchDataLoader.<Nation>getLoader("nation").load(Paths.get(config.tablePath, "nation.tbl").toString()));

        Future<List<Region>> regionsFuture = executor.submit(() ->
            TpchDataLoader.<Region>getLoader("region").load(Paths.get(config.tablePath, "region.tbl").toString()));


        List<Customer> customers = customersFuture.get();
        List<Order> orders = ordersFuture.get();
        List<LineItem> lineItems = lineItemsFuture.get();
        List<Supplier> suppliers = suppliersFuture.get();
        List<Nation> nations = nationsFuture.get();
        List<Region> regions = regionsFuture.get();

        executor.shutdown();

        System.out.println("Executing TPCH Query 5...");
         Map<String, Double> results = Query5Processor.execute(
                 customers, orders, lineItems, suppliers, nations, regions,
                 config.regionName, config.startDate, config.endDate
         );

        System.out.println("Writing results to: " + config.resultPath);
        ResultWriter.writeResults(config.resultPath, results);
        long endTime = System.currentTimeMillis();
        long durationInMillis = (endTime - startTime);
        System.out.println("Execution Time: " + durationInMillis + " ms" + "\n" + "Done");
    }
}