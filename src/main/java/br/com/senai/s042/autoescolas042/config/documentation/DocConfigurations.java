package br.com.senai.s042.autoescolas042.config.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                        .info(new Info()
                                .title("Auto Escola S042")
                                .description("Sistema de agendamento de instruções")
                                .contact(new Contact()
                                        .name("SENAI")
                                        .email("suporte@sp.senai.br"))
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://meudominioficticio.com.br/license")
                                )
                        );
    }
}
