package com.example.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Contact contact = new Contact()
                .name("Nea Representatives API");

        Info info = new Info()
                .title("API Упълномощени лица – НБД")
                .version("1.0")
                .description("Mock на API от системата НБД")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
