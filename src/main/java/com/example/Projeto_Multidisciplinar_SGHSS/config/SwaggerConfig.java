package com.example.Projeto_Multidisciplinar_SGHSS.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde")
                        .version("1.0.0")
                        .description("API RESTful desenvolvida para a centralização de dados clínicos da rede VidaPlus para a disciplina de Projeto Multidisciplinar - Trilha Back-end.")
                        .contact(new Contact().name("Ana Gabriela Brasil Fraiz - RU: 4877350").email("4877350@alunouninter.com")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
