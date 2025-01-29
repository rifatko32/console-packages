package ru.hofftech.consolepackages.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Посылки",
                version = "1.0.0",
                description = "Сервис обработки посылок",
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
