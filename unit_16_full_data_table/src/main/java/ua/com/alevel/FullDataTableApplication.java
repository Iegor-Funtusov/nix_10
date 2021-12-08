package ua.com.alevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class FullDataTableApplication {

    @Value("${init.db}")
    private Boolean init;

    private final DBTestFullDataTable testFullDataTable;

    public FullDataTableApplication(DBTestFullDataTable testFullDataTable) {
        this.testFullDataTable = testFullDataTable;
    }

    public static void main(String[] args) {
        SpringApplication.run(FullDataTableApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterMain() {
        if (init) {
            testFullDataTable.init();
        }
        testFullDataTable.searchByParams();
    }
}
