package com.example;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class ReactorHttpEchoServer {

    public static void main(String[] args) throws IOException {
        new ReactorHttpEchoServer().start();
    }

    public void start() throws IOException {
        long startAt = System.currentTimeMillis();
        HttpServer.create()
            .handle(new BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>>() {
                @Override
                public Publisher<Void> apply(HttpServerRequest req, HttpServerResponse resp) {
                    return resp.sendString(Mono.just("Hello from reactor!"));
                }
            })
            .port(9000).bind()
            .doOnNext(new Consumer<DisposableServer>() {
                @Override
                public void accept(DisposableServer disposableServer) {
                    System.out.printf("Application Started in %ss", (System.currentTimeMillis() - startAt) / 1000.0);
                }
            })
            .block();
        System.in.read();
    }

}
