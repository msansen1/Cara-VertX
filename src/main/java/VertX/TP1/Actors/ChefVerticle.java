package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;

public class ChefVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("Start of Chef Verticle");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Chef Verticle");
  }
}
