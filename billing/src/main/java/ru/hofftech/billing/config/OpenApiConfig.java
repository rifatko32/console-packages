package ru.hofftech.billing.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Биллинг посылок",
                version = "1.0.0",
                description = "Сервис обработки счетов биллинга посылок",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Rifat Yakhin",
                        email = "nagibator89@example.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Локальный сервер")
        }
)
public class OpenApiConfig {
}
