package com.cara.vertx.Verticles;

import io.vertx.core.AbstractVerticle;

public class ClientVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    System.out.println("Start of Client Verticle");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Client Verticle");
  }
}
