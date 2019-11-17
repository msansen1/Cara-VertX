package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;


public class ChefVerticle extends AbstractVerticle {
  final String chefAddress="Chef.address";
  final String ServeurAddress="Serveur.address";
  @Override
  public void start() throws Exception {
    System.out.println("Start of Chef Verticle");
    //Recevoir un message
    final EventBus bus = vertx.eventBus();
    final MessageConsumer<String> consumer = bus.consumer(chefAddress);
      consumer.handler(message -> {
        System.out.println("[Chef] <- " + message.body());
      });
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Chef Verticle");
  }
}
