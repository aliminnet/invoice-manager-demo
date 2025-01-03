package com.ali.min.invoicemanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Invoice Manager Demo Project OpenAPI Docs")
                        .version("1.0.0").description("Initial Demo for Invoice Manager Application"));
    }

}
