package ua.com.alevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class CustomExecutorService {

    ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    private synchronized void increment() {
        count = count + 1;
    }

    private void reentrantLockIncrement() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::reentrantLockIncrement));
        stop(executor);
        System.out.println(count);
    }

    private void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
