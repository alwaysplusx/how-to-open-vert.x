package com.example.utils;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuxin
 */
@Slf4j
public class VertxRunner {

    public static Future<String> run(Class<? extends Verticle> clazz) {
        Vertx vertx = Vertx.vertx();
        return vertx.deployVerticle(clazz, new DeploymentOptions())
            .onSuccess(s -> log.info("{} is running", clazz.getSimpleName()))
            .onFailure(e -> log.error("{} run failed", clazz.getSimpleName(), e));
    }

}
