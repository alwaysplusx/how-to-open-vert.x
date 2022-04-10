package com.example;

import com.example.utils.VertxRunner;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFileVerticle extends AbstractVerticle {

    public static void main(String[] args) {
        VertxRunner.run(ReadFileVerticle.class);
    }

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.get("/file-content").handler(this::readFileContent);
        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080);
    }

    private void readFileContent(RoutingContext ctx) {
        String path = ctx.request().getParam("path");
        log.info("read file from: {}", path);
        vertx.fileSystem()
            .readFile(path)
            .onSuccess(buf -> {
                ctx.response().end(buf);
                log.info("read file success");
            })
            .onFailure(err -> {
                ctx.response().end("read file failed");
                log.info("read file success");
            });
        log.info("main thread read file end!");
    }

}
