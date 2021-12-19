package ua.com.alevel;

import java.util.Random;

public class ExceptionTest extends Thread {

    public ExceptionTest() {}

    public ExceptionTest(String threadName) {
        super(threadName);
    }

    @Override
    public void start() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1) {
            System.out.println(this.getName());
        } else {
            throw new RuntimeException("Беда!!!");
        }
    }
}
