package ua.com.alevel;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ThreadCallable {

    private Long[] longs;
    private Long sum = 0L;

    public void call() {
        Callable task = () -> {
            try {
                return MathUtil.sum(longs);
            }
            catch (Exception e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        try {
            this.sum = (Long) task.call();
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
