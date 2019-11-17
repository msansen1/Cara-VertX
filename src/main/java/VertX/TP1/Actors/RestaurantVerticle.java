package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;

public class RestaurantVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("Start of Restaurant Verticle");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Restaurant Verticle");
  }
}
