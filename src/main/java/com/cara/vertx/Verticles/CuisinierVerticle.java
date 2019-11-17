package com.cara.vertx.Verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

public class CuisinierVerticle extends AbstractVerticle {

  final String CuisinierAddress="Cuisinier.address";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Cuisinier Verticle");
    //Recevoir un message
    final EventBus bus = vertx.eventBus();
    final MessageConsumer<String> consumer = bus.consumer(CuisinierAddress);
    consumer.handler(message -> {
      System.out.println("[Cuisinier] <- " + message.body());
    });
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Cuisinier Verticle");
  }
}
