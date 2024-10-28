package com.bdb.gestortareasws.web.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Gestor de Tareas API")
                        .version("1.0")
                        .description("Documentación de la API para el gestor de tareas")
                        .contact(new Contact().name("Tu Nombre").email("tuemail@ejemplo.com")));
    }
    /*@Bean
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
    }*/
}

