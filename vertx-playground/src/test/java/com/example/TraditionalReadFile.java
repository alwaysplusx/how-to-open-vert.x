package com.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class TraditionalReadFile {

  public static void main(String[] args) {
    log.info("start reading file");
    readFile("README.md", ar -> {
      if (ar.succeeded()) {
        log.info("file content: {}", ar.result().length);
      } else {
        log.info("can't read file", ar.cause());
      }
    });
    // System.exit(0);
  }

  public static void readFile(String path, Handler<AsyncResult<byte[]>> handler) {
    new Thread(() -> {
      try {
        byte[] content = IOUtils.toByteArray(new FileInputStream(path));
        handler.handle(Future.succeededFuture(content));
      } catch (IOException e) {
        handler.handle(Future.failedFuture(e));
      }
    }).start();
  }

}
