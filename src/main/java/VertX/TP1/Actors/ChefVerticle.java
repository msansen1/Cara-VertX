package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;


public class ChefVerticle extends AbstractVerticle {
  final String address="CHEF";
  @Override
  public void start() throws Exception {
    System.out.println("Start of Chef Verticle");
    //Recevoir un message
    final EventBus bus = vertx.eventBus();
    final MessageConsumer<String> consumer = bus.consumer(address);
      consumer.handler(message -> {
        System.out.println("incoming message: " + message.body());
      });
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Chef Verticle");
  }
}
