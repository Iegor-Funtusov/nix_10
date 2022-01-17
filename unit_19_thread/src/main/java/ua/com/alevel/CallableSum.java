package ua.com.alevel;

import java.util.concurrent.Callable;

public class CallableSum implements Callable<Long> {

    private Long[] longs;

    @Override
    public Long call() throws Exception {
        Thread.sleep(2000);
        return MathUtil.sum(longs);
    }

    public void setLongs(Long[] longs) {
        this.longs = longs;
    }
}
