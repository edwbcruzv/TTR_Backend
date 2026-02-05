//package com.escom.CreadorPracticas.Config.doc;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.security.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("API de ")
//                        .version("1.0.0")
//                        .description("Antes de ejecutar una peticion se tiene que solicitar el token y ponerlo en el espacio correspondiente"))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components().addSecuritySchemes("bearerAuth",
//                                new SecurityScheme()
//                                        .name("Authorization")
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")
//                                        .flows(
//                                                new OAuthFlows()
//                                                        .password(new OAuthFlow()
//                                                                .tokenUrl("/auth") // aquí tu endpoint real
//                                                                .scopes(new Scopes()
//                                                                        .addString("read", "Leer datos")
//                                                                        .addString("write", "Escribir datos")
//                                                                )
//                                                        )
//                                        )
//                        )
//
//                );
//    }
//}
