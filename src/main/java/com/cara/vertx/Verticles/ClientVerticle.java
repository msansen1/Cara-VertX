package com.cara.vertx.Verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class ClientVerticle extends AbstractVerticle {


  final String clientAddress="restaurant.client";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Client Verticle");

    final EventBus bus = vertx.eventBus();
    bus.send(clientAddress,"Bonjour, auriez vous une table disponible?");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Client Verticle");
  }
}
