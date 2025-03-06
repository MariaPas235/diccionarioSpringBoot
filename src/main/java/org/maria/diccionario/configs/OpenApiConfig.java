package org.maria.diccionario.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "diccionario CRUD",
                version = "1.0.0",
                description = "Esto es un CRUD para manejar un diccionario"
        )
)
public class OpenApiConfig {
    //swagger-ui/index.html 
}
