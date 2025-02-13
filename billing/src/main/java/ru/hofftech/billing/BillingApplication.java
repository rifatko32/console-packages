package ru.hofftech.billing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BillingApplication {
    public static void main(String[] args) {
        log.info("Starting billing application...");
        SpringApplication.run(BillingApplication.class, args);
    }
}
