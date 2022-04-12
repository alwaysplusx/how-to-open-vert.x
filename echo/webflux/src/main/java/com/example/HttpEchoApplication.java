package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
@SpringBootApplication
public class HttpEchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpEchoApplication.class, args);
    }

    @ResponseBody
    @GetMapping(path = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> echo() {
        return Mono.just("Hello from webflux!");
    }

}
