package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private volatile boolean isExecuting = false;

    private FixedThreadPool(int threadsCount) {
        threads = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted() && isExecuting) {
                    try {
                        tasks.take().run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }
    }

    public static FixedThreadPool create(int threadsCount) {
        if (threadsCount < 1) {
            throw new IllegalArgumentException("Количество потоков не должно быть меньше 1");
        }
        return new FixedThreadPool(threadsCount);
    }

    @Override
    public void start() {
        isExecuting = true;
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (!isExecuting) {
            throw new IllegalStateException("Работа пула потоков уже завершилась");
        }
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        isExecuting = false;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
