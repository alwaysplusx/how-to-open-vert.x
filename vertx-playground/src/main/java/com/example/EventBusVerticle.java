package com.example;

import com.example.utils.VertxRunner;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuxin
 */
@Slf4j
public class EventBusVerticle extends AbstractVerticle {

    private static final String address = "addr-ping-pong";

    public static void main(String[] args) {
        VertxRunner.run(EventBusVerticle.class);
    }

    @Override
    public void start() throws Exception {
        vertx.eventBus()
            .consumer(address)
            .handler(msg -> {
                log.info("receive message: {}", msg.body());
                msg.reply("pong");
            });

        vertx.setPeriodic(5_000, t -> {
            vertx.eventBus()
                .request(address, "ping")
                .onSuccess(msg -> {
                    log.info("receive reply message: {}", msg.body());
                });
        });
    }

}
