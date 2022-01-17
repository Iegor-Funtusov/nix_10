package ua.com.alevel;

import java.util.concurrent.Callable;

public class CustomCallable {

    private Long[] longs;
    private Long sum = 0L;

    public void run() {
//        Callable<Long> task = new Callable<Long>() {
//            @Override
//            public Long call() throws Exception {
//                return MathUtil.sum(longs);
//            }
//        };

        Callable<Long> task = () -> MathUtil.sum(longs);
        try {
            this.sum = task.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLongs(Long[] longs) {
        this.longs = longs;
    }

    public Long getSum() {
        return sum;
    }
}
