package edu.hw10.task2;

import java.util.Map;

public interface CacheWrapper {
    Object get(String key);

    void put(String key, Object value);

    Map<String, Object> getCache();
}
