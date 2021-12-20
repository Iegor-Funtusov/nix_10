package ua.com.alevel;

import java.util.Random;

public class RunnubleTest implements Runnable {

    @Override
    public void run() {
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 1) {
            System.out.println(Thread.currentThread().getName());
        } else {
            throw new CustomException("Беда!!!");
        }
    }
}
