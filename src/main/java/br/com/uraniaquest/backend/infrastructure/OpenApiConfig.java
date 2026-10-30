package br.com.uraniaquest.backend.infrastructure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UrâniaQuest API - Challenge AMAZUL & CTMSP")
                        .version("1.0.0")
                        .description("API RESTful de apoio à plataforma educativa UrâniaQuest, voltada à divulgação científica dos benefícios da tecnologia nuclear.")
                        .contact(new Contact()
                                .name("Equipe UrâniaQuest - FIAP 2TDSPA")
                                .url("https://github.com/PedroBegali/urania_backend")));
    }
}