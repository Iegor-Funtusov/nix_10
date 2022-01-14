package ua.com.alevel;

public class CustomThread extends Thread {

    public CustomThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName = " + threadName);
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println(threadName + " stop");
    }
}
