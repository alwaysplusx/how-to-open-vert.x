package com.example;

import com.example.utils.VertxRunner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
public class SockjsVerticle extends AbstractVerticle {

    private static final String address = "addr-sockjs";

    public static void main(String[] args) {
        VertxRunner.run(SockjsVerticle.class);
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        SockJSBridgeOptions options = new SockJSBridgeOptions()
            .addOutboundPermitted(new PermittedOptions().setAddress(address));
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        // mount the bridge on the router
        router.mountSubRouter("/eventbus", sockJSHandler.bridge(options));

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8888);

//        vertx.eventBus()
//            .consumer(address)
//            .handler(msg -> {
//                log.info("receive message: {}", msg.body());
//                msg.reply(new JsonObject().put("now", System.currentTimeMillis()));
//            });

        vertx.setPeriodic(1_000, t -> {
            vertx.eventBus()
                .request(address, new JsonObject().put("now", System.currentTimeMillis()))
                .onSuccess(msg -> {
                    log.info("reply message: {}", msg.body());
                });
        });

    }

}
