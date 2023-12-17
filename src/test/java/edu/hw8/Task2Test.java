package edu.hw8;

import edu.hw8.task2.FibonacciTask;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

class Task2Test {
    private final Map<Integer, Long> fibonacci = Map.of(
        0, 1L,
        1, 1L,
        2, 2L,
        3, 6L,
        4, 24L,
        10, 3628800L,
        12, 479001600L,
        13, 6227020800L,
        19, 121645100408832000L,
        20, 2432902008176640000L
    );

    @Test
    void threadPoolTest() throws Exception {
        Map<Integer, FibonacciTask> tasks = new HashMap<>();
        CountDownLatch latch = new CountDownLatch(fibonacci.keySet().size());
        try (ThreadPool pool = FixedThreadPool.create(5)) {
            pool.start();
            for (int n : fibonacci.keySet()) {
                FibonacciTask task = new FibonacciTask(n, latch);
                tasks.put(n, task);
                pool.execute(task);
            }
            try {
                latch.await();
            } catch (InterruptedException ignored) {
            }
        }
        for (int n : fibonacci.keySet()) {
            Assertions.assertEquals(fibonacci.get(n), tasks.get(n).getResult());
        }
    }

    @Test
    void threadPoolCreationExceptionTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> FixedThreadPool.create(0));
    }
}
