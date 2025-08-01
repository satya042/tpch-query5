# TPCH Query 5 Java Multithreading Project

## Overview
Task is to implement TPCH Query 5 using Java and multithreading. 
1. Understand the query 5 given below.
2. Generate input data for the query using [TPCH Data Generation Tool](https://github.com/electrum/tpch-dbgen)

## Query 5
```
select 
n_name, 
sum(l_extendedprice * (1 - l_discount)) as revenue 
from 
customer, 
orders, 
lineitem, 
supplier, 
nation, 
region 
where 
c_custkey = o_custkey 
and l_orderkey = o_orderkey 
and l_suppkey = s_suppkey 
and c_nationkey = s_nationkey 
and s_nationkey = n_nationkey 
and n_regionkey = r_regionkey 
and r_name = 'ASIA' 
and o_orderdate >= '1994-01-01' 
and o_orderdate < '1995-01-01' 
group by 
n_name 
order by 
revenue desc 
```

## ⚙️ Prerequisites

- Java 11 or higher
- Maven 3.x
- Git (optional)
- TPCH `.tbl` files (must be placed in specified folder)

## Building the Project
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd tpch-query5
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Place TPCH data files:
  - Download or generate TPC-H .tbl files (use scale factor as needed) and place them inside the your desired folder.
  - Make sure to update the file paths accordingly as per below arguments.

## Running the Program
### Single-Threaded Execution
To run the program in single-threaded mode, use the following command:
```bash
./tpch_query5 --r_name ASIA --start_date 1994-01-01 --end_date 1995-01-01 --threads 1 --table_path /path/to/tables --result_path /path/to/results
```

### Multi-Threaded Execution
To run the program in multi-threaded mode, specify the number of threads (e.g., 4):
```bash
./tpch_query5 --r_name ASIA --start_date 1994-01-01 --end_date 1995-01-01 --threads 4 --table_path /path/to/tables --result_path /path/to/results
```

## Generating a Report
1. Run the program with the desired parameters.
2. The results will be output to the specified result path.
3. Analyze the results to compare performance between single-threaded and multi-threaded execution.

## Rationale Behind Speedup
- **Parallelization**: By dividing the workload among multiple threads, the program can process data concurrently, reducing the overall execution time.
- **Efficiency**: Multithreading is particularly effective for I/O-bound tasks (like reading data) and CPU-bound tasks (like processing and joining tables).
- **Scalability**: The speedup is expected to scale with the number of threads, up to the point where the overhead of thread management becomes significant.

## Additional Notes
- Ensure that the TPCH data is correctly generated and placed in the specified table path.
- Adjust the number of threads based on your system's capabilities and the size of the dataset.

## Troubleshooting
If you encounter any issues during build or execution, please check the following:
- Ensure all dependencies are installed.
- Verify that the TPCH data is correctly formatted and accessible.
- Check the command line arguments for correctness.
