package edu.hw9.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

class Task1Test {
    private static final int THREADS_COUNT = 4;
    ExecutorService service = Executors.newFixedThreadPool(THREADS_COUNT);

    @RepeatedTest(100)
    void statsZeroHighLoadTest() throws ExecutionException, InterruptedException {
        Map<Long, Metric> expected = new HashMap<>();
        MetricName[] names = MetricName.values();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        StatsCollector collector = new StatsCollector();
        CountDownLatch latch = new CountDownLatch(1_000);
        for (int i = 0; i < 1_000; i++) {
            long id = i;
            MetricName name = names[random.nextInt(names.length)];
            double[] data = new double[100];
            service.submit(() ->  {
                collector.push(id, name, data);
                latch.countDown();
            });
            expected.put(id, new Metric(name, 0));
        }
        latch.await();
        Map<Long, Metric> actual = collector.stats();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void statsTest() throws InterruptedException, ExecutionException {
        Map<Long, double[]> data = Map.of(
            0L, new double[] {1, 2, 3, 4},
            1L, new double[] {0.5, -55.8},
            2L, new double[] {1_000, 2, 3, 4}
        );
        Map<Long, MetricName> names = Map.of(
            0L, MetricName.AVG,
            1L, MetricName.MIN,
            2L, MetricName.SUM
        );
        Map<Long, Metric> expected = Map.of(
            0L, new Metric(MetricName.AVG, 2.5),
            1L, new Metric(MetricName.MIN, -55.8),
            2L, new Metric(MetricName.SUM, 1_009)
        );
        StatsCollector collector = new StatsCollector();
        CountDownLatch latch = new CountDownLatch(data.size());
        for (long id : data.keySet()) {
            service.submit(() ->  {
                collector.push(id, names.get(id), data.get(id));
                latch.countDown();
            });
        }
        latch.await();
        Map<Long, Metric> actual = collector.stats();
        Assertions.assertEquals(expected, actual);
    }
}
