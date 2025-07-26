package com.tpch.query5.loader;

import java.io.IOException;
import java.util.List;

public interface DataLoader<T> {
    List<T> load(String filePath) throws IOException;
}
