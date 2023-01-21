package com.ifood.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .description("Lorem ipsum")
                        .version("1.0")
                        .license(new License().name("MIT"))
                        .title("IFOOD API BASED WITH SPRINGBOOT AND JAVA 17")
                );

    }
}
