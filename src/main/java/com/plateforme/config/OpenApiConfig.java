package com.plateforme.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("Plateforme E-commerce API")
                .description("API Spring Boot pour gestion de vagues, produits, commandes et livraisons")
                .version("v1.0.0")
                .contact(new Contact().name("Plateforme").email("support@example.com")))
            .externalDocs(new ExternalDocumentation().description("Swagger UI"));
    }
}
