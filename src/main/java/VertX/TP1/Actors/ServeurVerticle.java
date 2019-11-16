package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;

public class ServeurVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
