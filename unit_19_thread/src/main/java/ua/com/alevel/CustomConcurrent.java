package ua.com.alevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class CustomConcurrent {

    private long count = 0L;
    private final ReentrantLock reentrantLock = new ReentrantLock();

//    private synchronized void increment() {
//        ++count;
//    }

//    private void increment() {
//        synchronized (this) {
//            ++count;
//        }
//    }

    private void increment() {
        reentrantLock.lock();
        try {
            ++count;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void concurrent() {
        ExecutorService executorService = Executors.newFixedThreadPool(24);
        IntStream.range(0, 10000)
                .forEach(i -> executorService.submit(this::increment));
        stop(executorService);
        System.out.println(count);
//        List<IncrementThread> threadList = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            IncrementThread thread = new IncrementThread("customThread" + i);
//            thread.start();
//            threadList.add(thread);
//        }
//        Random random = new Random();
//        for (int i = 0; i < 1_000; i++) {
//            IncrementThread thread = threadList.get(random.nextInt(0, 4));
//            System.out.println("thread = " + thread.getName());
////            if (thread.isInterrupted()) {
////                thread.start();
////            }
//            increment(thread.increment(count));
////            thread.interrupt();
//        }
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

    public long getCount() {
        return count;
    }
}
