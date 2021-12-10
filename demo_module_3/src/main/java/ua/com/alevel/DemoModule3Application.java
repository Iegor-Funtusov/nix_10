package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoModule3Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoModule3Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        DbInMemory.getInstance().init();
    }
}
