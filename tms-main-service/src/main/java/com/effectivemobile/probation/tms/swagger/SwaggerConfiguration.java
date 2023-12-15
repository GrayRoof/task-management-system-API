package com.effectivemobile.probation.tms.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("Task Management System REST API")
                        .description(getDescription())
                        .version("1.0").contact(new Contact()
                                .name("Шаймиева Маргарита")
                                .email( "1grayroof@gmail.com")
                                .url("https://github.com/GrayRoof?tab=repositories")));

    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private String getDescription() {
        return """
                Простой API в качестве тестового задания.\s
                Система обеспечивает создание, редактирование, удаление и просмотр задач.\s
                Каждая задача содержитт заголовок, описание, статус, приоритет, автора задачи и исполнителя.\s
                Пользователи могут управлять своими задачами:
                 - создавать новые,
                 - редактировать существующие,
                 - просматривать и удалять,
                 - менять статус
                 - назначать исполнителей задачи\s
                Пользователи могут просматривать задачи других пользователей.\s
                Исполнители задачи могут менять статус своих задач.\s
                К задачам можно оставлять комментарии.\s
                API позволяет получать задачи конкретного автора или исполнителя, а также все комментарии к ним.\s
                API обеспечивает фильтрацию и пагинацию вывода.
                """;
    }
}