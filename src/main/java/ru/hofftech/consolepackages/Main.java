package ru.hofftech.consolepackages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        log.info("Starting console packages...");
        SpringApplication.run(Main.class, args);
    }
}