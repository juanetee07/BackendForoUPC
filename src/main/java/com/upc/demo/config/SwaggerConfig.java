package com.upc.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Backend Foro UPC")
                        .version("1.0")
                        .description("API para gestión de resoluciones académicas")
                        .contact(new Contact()
                                .name("Equipo Backend")
                                .email("equipo@upc.com")));
    }
}