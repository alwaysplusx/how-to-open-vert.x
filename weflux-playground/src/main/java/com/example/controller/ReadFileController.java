package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufMono;

@Slf4j
@Controller
public class ReadFileController {

    @RequestMapping(path = "/file-content")
    public void fileContent(@RequestParam String path, ServerWebExchange webExchange) {
        log.info("read file from: {}", path);
    }

}
