package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProgramRun {

    private static final Long[] longs = MathUtil.generateRandomArray(1_000_000);

    public static void run() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                nav();
//                Thread.getAllStackTraces().keySet().stream()
//                        .filter(local -> local.getName().startsWith("Thread-")).
//                        forEach((t) -> System.out.println(t.getName()));
                String line = reader.readLine();
                menu(line);
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private static void menu(String line) {
        switch (line) {
            case "0" -> System.exit(0);
            case "1" -> correctStopThread();
            case "2" -> incorrectStopThread();
            case "3" -> simpleSum();
            case "4" -> complexSum();
            case "5" -> executor();
            case "6" -> callable();
        }
    }

    private static void nav() {
        System.out.println();
        System.out.println("if you want run correctStopThread, please enter 1");
        System.out.println("if you want run incorrectStopThread, please enter 2");
        System.out.println("if you want run simpleSum, please enter 3");
        System.out.println("if you want run complexSum, please enter 4");
        System.out.println("if you want run executor, please enter 5");
        System.out.println("if you want run Callable, please enter 6");
        System.out.println("if you want run exit, please enter 0");
        System.out.println();
    }

    private static void correctStopThread() {
        System.out.println("ProgramRun.correctStopThread");
        Thread thread = new CustomThread("correct ivan ");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private static void incorrectStopThread() {
        System.out.println("ProgramRun.incorrectStopThread");
        Thread thread = new CustomThread("incorrect ivan");
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }

    private static void simpleSum() {
        System.out.println("ProgramRun.simpleSum start");
        long start = System.currentTimeMillis();
        System.out.println("sum: " + MathUtil.sum(longs));
        long end = System.currentTimeMillis() - start;
        System.out.println("ProgramRun.simpleSum finish: " + end + ".ms");
    }

    private static void complexSum() {
        System.out.println("ProgramRun.complexSum start");
        List<Long[]> list = MathUtil.divideArray(longs);
        List<Long[]> listNew = new ArrayList<>();
        for (Long[] longs1 : list) {
            listNew.addAll(MathUtil.divideArray(longs1));
        }
        System.out.println("listNew = " + listNew.size());
        Long sum = 0L;
//        List<SumThread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (Long[] innerArr : listNew) {
            SumThread sumThread = new SumThread();
            sumThread.setLongs(innerArr);
            sumThread.start();
            try {
                sumThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            sum += sumThread.getSum();
//            System.out.println("sum = " + sum);

//            while (!sumThread.isDone()) { }
            sum += sumThread.getSum();
            System.out.println("sum = " + sum);
            sumThread.interrupt();
//            threads.add(sumThread);
        }

//        boolean done = true;
//        while (done) {
//            int a = 0;
//            for (SumThread thread : threads) {
//                if (thread.isDone()) {
//                    a += 1;
//                }
//            }
//            if (a == threads.size()) {
//                for (SumThread thread : threads) {
//                    sum += thread.getSum();
//                }
//                done = false;
//            }
//        }

        long end = System.currentTimeMillis() - start;
        System.out.println("sum: " + sum);
        System.out.println("ProgramRun.complexSum finish: " + end + ".ms");


//        await int as = async();
//        System.out.println("as = " + as);
    }

//    private async int async() {
//        System.out.println("ProgramRun.async");
//        return 888;
//    }

    private static void executor() {
        CustomExecutorService executorService = new CustomExecutorService();
        executorService.test();
    }

    private static void callable() {
        System.out.println("ProgramRun.callable");
        List<Long[]> list = MathUtil.divideArray(longs);
        List<ThreadCallable> callables = new ArrayList<>();
        for (Long[] longs1 : list) {
            ThreadCallable threadCallable = new ThreadCallable();
            threadCallable.setLongs(longs1);
            threadCallable.call();
            callables.add(threadCallable);
        }

        long sum = 0L;
        for (ThreadCallable callable : callables) {
            sum += callable.getSum();
        }
        System.out.println("sum = " + sum);
    }

    private void other() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Long> future = executor.submit(() -> {
            try {
                return MathUtil.sum(longs);
            }
            catch (Exception e) {
                throw new IllegalStateException("task interrupted", e);
            }
        });

        System.out.println("future done? " + future.isDone());

        Long result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("future done? " + future.isDone());
        System.out.print("result: " + result);
    }
}
