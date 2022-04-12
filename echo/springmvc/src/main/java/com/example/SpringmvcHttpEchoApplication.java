package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class SpringmvcHttpEchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcHttpEchoApplication.class, args);
    }

    @ResponseBody
    @GetMapping({"", "/", "/echo"})
    public String echo() {
        return "Hello from springmvc!";
    }

}
