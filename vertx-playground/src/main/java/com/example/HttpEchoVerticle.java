package com.example;

import com.example.utils.VertxRunner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpEchoVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        long startAt = System.currentTimeMillis();
        VertxRunner.run(HttpEchoVerticle.class)
            .onSuccess(res -> {
                log.info("Application Started in {}s", (System.currentTimeMillis() - startAt) / 1000.0);
            });
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        vertx.createHttpServer()
            .requestHandler(req -> {
                req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
            })
            .listen(8888, http -> {
                if (http.succeeded()) {
                    startPromise.complete();
                    log.info("HTTP server started on port 8888");
                } else {
                    startPromise.fail(http.cause());
                }
            });
    }
}
