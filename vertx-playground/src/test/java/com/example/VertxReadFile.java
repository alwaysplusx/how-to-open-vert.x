package com.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VertxReadFile {

  public static void main(String[] args) {
    Vertx.vertx()
        .fileSystem()
        .readFile("README.md", new Handler<AsyncResult<Buffer>>() {
          @Override
          public void handle(AsyncResult<Buffer> event) {
            log.info("read file content: {}", event.result().toString());
          }
        });
  }


}
