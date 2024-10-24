package com.bdb.gestortareasws.web.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ExampleBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.Example;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.Response;

import static com.bdb.gestortareasws.utilitarios.Constantes.RESP_404;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {

        Example nf = new ExampleBuilder().description("404").id("404").value(RESP_404).mediaType(MediaType.ALL_VALUE).build();
        List<Example> nfl = new ArrayList<>();
        nfl.add(nf);
        List<Response> respuestasGet = new ArrayList<>();
        respuestasGet.add(new ResponseBuilder().code("401").description("No Autorizado: Token inválidoA").build());
        respuestasGet.add(new ResponseBuilder().code("400").description("Datos de entrada imcompletos o erroneos.").build());
        respuestasGet.add(new ResponseBuilder().code("200").description("Respuesta exitosa").build());
        respuestasGet.add(new ResponseBuilder().code("404").description("No se encontro información").examples(nfl).build()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bdb.gestortareasws.web.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .globalResponses(HttpMethod.GET, respuestasGet)
                .globalResponses(HttpMethod.POST, respuestasGet);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Gestor de tareas",
                "API Gestor de tareas",
                "0.0.1",
                "http://codmind.com/terms",
                new Contact("Codmind", "https://codmind.com", "apis@codmind.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}

