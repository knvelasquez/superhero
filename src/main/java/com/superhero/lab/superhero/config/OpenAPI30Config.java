package com.superhero.lab.superhero.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Super Hero REST API",
        version = "3.0",
        contact = @Contact(name = "Kevin Velásquez", email = "knvelasquez@outlook.com")),
        security = {@SecurityRequirement(name = "basicAuth"), @SecurityRequirement(name = "bearerToken")}
)
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"),
        //@SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic")
})
public class OpenAPI30Config {

}
