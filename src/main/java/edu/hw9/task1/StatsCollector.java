package edu.hw9.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    private static final int DEFAULT_THREADS_COUNT = 4;
    private final ExecutorService service;
    Map<Long, Future<Metric>> futures = Collections.synchronizedMap(new HashMap<>());

    public StatsCollector() {
        this(DEFAULT_THREADS_COUNT);
    }

    public StatsCollector(int threadsCount) {
        service = Executors.newFixedThreadPool(threadsCount);
    }

    public void push(long id, MetricName name, double[] data) {
        futures.put(id, service.submit(() -> name.count(data)));
    }

    public Map<Long, Metric> stats() throws ExecutionException, InterruptedException {
        service.shutdown();
        Map<Long, Metric> metrics = new HashMap<>();
        for (long id : futures.keySet()) {
            metrics.put(id, futures.get(id).get());
        }
        return metrics;
    }
}
