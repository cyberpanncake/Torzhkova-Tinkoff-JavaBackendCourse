package edu.hw10.task2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RuntimeCacheWrapper implements CacheWrapper {
    private final Map<String, Object> cacheMap = new HashMap<>();

    @Override
    public Object get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public void put(String key, Object value) {
        cacheMap.put(key, value);
    }

    @Override
    public Map<String, Object> getCache() {
        return Collections.unmodifiableMap(cacheMap);
    }
}
