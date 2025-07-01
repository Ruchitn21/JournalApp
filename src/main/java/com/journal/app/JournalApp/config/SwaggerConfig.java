package com.journal.app.JournalApp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomerConfig() {
        return new OpenAPI()
                .info(new Info().title("Journal App APIs").description("JournalApp is a modern, full-featured personal digital journal application built with Spring Boot and MongoDB. It provides users with a seamless experience for creating, managing, and organizing their personal thoughts, experiences, and daily reflections in a secure and intuitive platform."))
                .servers(Arrays.asList(new Server().url("http://localhost:8081").description("localhost server"),
                        new Server().url("https://serene-bayou-95125-b5ba1e9f2f10.herokuapp.com").description("production server"))).
                tags(Arrays.asList(
                        new Tag().name("Public APIs"),
                        new Tag().name("User APIs"),
                        new Tag().name("Journal APIs"),
                        new Tag().name("Admin APIs")
                )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes(
                        "bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                ));
    }
}
