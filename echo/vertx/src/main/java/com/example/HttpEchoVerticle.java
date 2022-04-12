package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class HttpEchoVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        long startAt = System.currentTimeMillis();
        Vertx.vertx()
            .deployVerticle(HttpEchoVerticle.class, new DeploymentOptions())
            .onSuccess(res -> {
                System.out.printf("Application Started in %ss\n", (System.currentTimeMillis() - startAt) / 1000.0);
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
            .listen(9000);
    }
}
