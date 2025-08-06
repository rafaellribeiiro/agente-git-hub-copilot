package com.livraria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Livraria")
                        .version("1.0")
                        .description("API REST para gerenciamento de livros, autores e categorias de uma livraria")
                        .contact(new Contact()
                                .name("Administrador")
                                .email("admin@livraria.com"))
                        .license(new License()
                                .name("Licença Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
