package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@Slf4j
@RestController
public class ReadFileController {

    @GetMapping("/file-content")
    public void fileContent(@RequestParam String path, NativeWebRequest webRequest) throws IOException {
        HttpServletResponse httpResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        log.info("read file from: {}", path);
        readFileAsync(path,
            new Consumer<String>() {
                @Override
                public void accept(String s) {
                    log.info("read file success: {}", s);
                    try {
                        httpResponse.getWriter().write(s);
                        httpResponse.getWriter().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Consumer<Throwable>() {
                @Override
                public void accept(Throwable err) {
                    log.info("read file failed.", err);
                    try {
                        httpResponse.getWriter().write("read file failed");
                        httpResponse.getWriter().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    public static void readFileAsync(String path, Consumer<String> successHandler, Consumer<Throwable> errorHandler) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = IOUtils.toString(new FileInputStream(path), StandardCharsets.UTF_8);
                    successHandler.accept(content);
                } catch (IOException e) {
                    errorHandler.accept(e);
                }
            }
        }).start();
    }

}
