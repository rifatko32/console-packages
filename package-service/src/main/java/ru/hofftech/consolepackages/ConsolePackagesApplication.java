package ru.hofftech.consolepackages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class ConsolePackagesApplication {
    public static void main(String[] args) {
        log.info("Starting console packages...");
        SpringApplication.run(ConsolePackagesApplication.class, args);
    }
}