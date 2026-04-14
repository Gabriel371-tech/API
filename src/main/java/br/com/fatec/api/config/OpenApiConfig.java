package br.com.fatec.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gerenciamento de Produtos")
                        .version("1.0")
                        .description("Esta API permite o cadastro, consulta, atualizacao e delecao de produtos no catalogo da Fatec.")
                        .contact(new Contact()
                                .name("Suporte Tecnico")
                                .email("suporte@fatec.com.br")));
    }
}
