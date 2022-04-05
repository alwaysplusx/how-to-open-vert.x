package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WefluxPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(WefluxPlaygroundApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> router() {
        return RouterFunctions.route()
            .GET("/", req -> ServerResponse.ok().bodyValue("Hello from webflux!"))
            .build();
    }

}
