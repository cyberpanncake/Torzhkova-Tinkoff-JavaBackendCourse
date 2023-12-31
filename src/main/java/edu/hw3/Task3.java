package edu.hw3;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3<T> {

    public Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> result = new HashMap<>();
        for (T obj : list) {
            result.putIfAbsent(obj, 0);
            result.put(obj, result.get(obj) + 1);
        }
        return Collections.unmodifiableMap(result);
    }
}
