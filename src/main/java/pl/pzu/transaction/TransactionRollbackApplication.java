package pl.pzu.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionRollbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionRollbackApplication.class, args);
    }

}
