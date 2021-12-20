package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class FinalProjectSupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectSupplierApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
//        for (int i = 0; i < 7; i++) {
//            Book book = new Book();
//            long bookId = i + 1;
//            book.setBookId(bookId);
//            book.setPrice(new BigDecimal("250.00"));
//            book.setQuantity(i);
//            bookRepository.save(book);
//        }
    }
}
