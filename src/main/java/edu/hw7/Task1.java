package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    private Task1() {
    }

    public static int incrementCounter(int threadCount) throws InterruptedException {
        if (threadCount < 1) {
            throw new IllegalArgumentException("Количество потоков должно быть больше 0");
        }
        AtomicInteger counter = new AtomicInteger(0);
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(counter::incrementAndGet);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        return counter.get();
    }
}
