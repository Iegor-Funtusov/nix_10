package ua.com.alevel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

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
            e.printStackTrace();
        }
    }

    private static void menu(String line) {
        switch (line) {
            case "0" -> System.exit(0);
            case "1" -> correctStopThread();
            case "2" -> incorrectStopThread();
            case "3" -> simpleSum();
            case "4" -> complexSum();
            case "5" -> callableSum();
            case "6" -> futureSumExecutorService();
            case "7" -> futureSumWithoutExecutorService();
            case "8" -> concurrent();
        }
    }

    private static void nav() {
        System.out.println();
        System.out.println("if you want run correctStopThread, please enter 1");
        System.out.println("if you want run incorrectStopThread, please enter 2");
        System.out.println("if you want run simpleSum, please enter 3");
        System.out.println("if you want run complexSum, please enter 4");
        System.out.println("if you want run callableSum, please enter 5");
        System.out.println("if you want run futureSum with ExecutorService, please enter 6");
        System.out.println("if you want run futureSum without ExecutorService, please enter 7");
        System.out.println("if you want run concurrent, please enter 8");
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
        List<SumThread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (Long[] innerArr : listNew) {
            SumThread sumThread = new SumThread();
            sumThread.setLongs(innerArr);
            sumThread.start();
            threads.add(sumThread);
//            try {
//                sumThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

//            sum += sumThread.getSum();
//            System.out.println("sum = " + sum);

//            while (!sumThread.isDone()) { }
//            sum += sumThread.getSum();
//            System.out.println("sum = " + sum);
//            sumThread.interrupt();
//            threads.add(sumThread);
        }

        boolean done = true;
        while (done) {
            int a = 0;
            for (SumThread thread : threads) {
                if (thread.isInterrupted()) {
                    a += 1;
                }
            }
            if (a == threads.size()) {
                for (SumThread thread : threads) {
                    sum += thread.getSum();
                }
                done = false;
            }
        }

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

    private static void concurrent() {
        CustomConcurrent concurrent = new CustomConcurrent();
        concurrent.concurrent();
        System.out.println("concurrent = " + concurrent.getCount());
    }

    private static void callableSum() {
        System.out.println("ProgramRun.callableSum start");
        List<Long[]> list = MathUtil.divideArray(longs);
        List<Long[]> listNew = new ArrayList<>();
        for (Long[] longs1 : list) {
            listNew.addAll(MathUtil.divideArray(longs1));
        }
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long[] longs1 : listNew) {
//            CustomCallable callable = new CustomCallable();
//            callable.setLongs(longs1);
//            callable.run();
//            sum += callable.getSum();
            Callable<Long> callable = () -> MathUtil.sum(longs1);
            try {
                sum += callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("sum: " + sum);
        System.out.println("ProgramRun.callableSum finish: " + end + ".ms");
    }

    private static void futureSumExecutorService() {
        System.out.println("ProgramRun.futureSum start");

        List<Long[]> list = MathUtil.divideArray(longs);
        List<Long[]> listNew = new ArrayList<>();
        for (Long[] longs1 : list) {
            listNew.addAll(MathUtil.divideArray(longs1));
        }
        Long sum = 0L;
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(listNew.size());

        for (Long[] longs1 : listNew) {
            System.out.println("next iter");
            Future<Long> longFuture = executorService.submit(() -> {
                Thread.sleep(1000);
                return MathUtil.sum(longs1);
            });
            try {
                sum += longFuture.get();
                System.out.println("sum = " + sum);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("sum: " + sum);
        System.out.println("ProgramRun.futureSum finish: " + end + ".ms");
    }

    private static void futureSumWithoutExecutorService() {
        System.out.println("ProgramRun.futureSumWithoutExecutorService");
        List<Long[]> list = MathUtil.divideArray(longs);
        List<Long[]> listNew = new ArrayList<>();
        for (Long[] longs1 : list) {
            listNew.addAll(MathUtil.divideArray(longs1));
        }
        Long sum = 0L;
        long start = System.currentTimeMillis();

//        CompletableFuture<Long> future = new CompletableFuture<>();
        for (Long[] longs1 : listNew) {
            System.out.println("next iter");

//            CompletableFuture<Long> completableFuture
//                    = CompletableFuture.supplyAsync(() -> MathUtil.sum(longs1));
//
//            future.thenApply(l -> {
//                try {
//                    return l + completableFuture.get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//                return 0;
//            });

            Callable<Long> callable = () -> MathUtil.sum(longs1);
            FutureTask<Long> futureTask = new FutureTask<>(callable);
            new Thread(futureTask).start();

//            FutureTask<Long> futureTask = new FutureTask<>(() -> MathUtil.sum(longs1));
//            new Thread(futureTask).start();

//            Callable<Long> callable = new Callable<Long>() {
//                @Override
//                public Long call() throws Exception {
//                    return MathUtil.sum(longs1);
//                }
//            };
//            FutureTask<Long> futureTask = new FutureTask<>(callable);
//            new Thread(futureTask).start();


            try {
                sum += futureTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

//        try {
//            sum = future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        long end = System.currentTimeMillis() - start;
        System.out.println("sum: " + sum);
        System.out.println("ProgramRun.futureSum finish: " + end + ".ms");



//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("ProgramRun.run");
//            }
//        };
//        runnable.run();
//        new Thread(runnable).start();
    }
}
