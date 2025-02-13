package ru.hofftech.billing.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.kafka.KafkaContainer;
import ru.hofftech.billing.service.PackageBillingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.hofftech.billing.utils.DateUtils.DATE_FORMAT;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/generate-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BillingRestControllerTest{

    final static String DATABASE_NAME = "billing_db";
    final static String DATABASE_USER = "postgres";
    final static String DATABASE_PASSWORD = "postgres";

    @Autowired
    MockMvc mockMvc;

    @Container
    public static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName(DATABASE_NAME)
            .withUsername(DATABASE_USER)
            .withPassword(DATABASE_PASSWORD);

    @Container
    public static KafkaContainer kafkaContainer = new KafkaContainer("apache/kafka-native:3.8.0");

    @BeforeAll
    static void beforeAll() {
        kafkaContainer.start();
        postgresqlContainer.start();
        System.setProperty("spring.datasource.url", postgresqlContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresqlContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresqlContainer.getPassword());
        System.setProperty("spring.kafka.bootstrap-servers", kafkaContainer.getBootstrapServers());
    }


    @Test
    void create_withValidRequest_shouldReturnValidResponse() throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("fromDate", "01.01.2025");
        params.add("toDate", "31.01.2025");

        String expectedResponseJson = """
                     {
                        "reportStrings": [
                         "01.01.2025; Погрузка; 2 машин; 20 посылок; 200 рублей",
                         "01.01.2025; Разгрузка; 1 машин; 10 посылок; 100 рублей"]
                     }
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/billing/client1")
                        .queryParams(params))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().json(expectedResponseJson));
    }
}
