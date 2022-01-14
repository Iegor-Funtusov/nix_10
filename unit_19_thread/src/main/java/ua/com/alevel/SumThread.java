package ua.com.alevel;

public class SumThread extends Thread {

    private Long[] longs;
    private Long sum = 0L;
    private boolean isDone;

    @Override
    public void run() {
        sum = MathUtil.sum(longs);
        isDone = true;
    }

    public void setLongs(Long[] longs) {
        this.longs = longs;
    }

    public Long getSum() {
        return sum;
    }

    public boolean isDone() {
        return isDone;
    }
}
