package br.com.erudio.rest_with_spring_boot_and_java_erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 23 and Spring Boot")
                        .version("v1")
                        .description("Some description about your API")
                        .termsOfService("https://github.com/XDedigas/rest-with-spring-boot-and-java-erudio")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/XDedigas/rest-with-spring-boot-and-java-erudio")));
    }
}
