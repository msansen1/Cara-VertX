package VertX.TP1.Actors;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class ServeurVerticle extends AbstractVerticle {

  final String message="[Serveur]:j'ai une commande";
  final String chefAddress="Chef.address";
  final String CuisinierAddress="Cuisinier.address";

  final int period = 3000;

  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
    final EventBus bus = vertx.eventBus();
    bus.send(chefAddress,message);
    vertx.setPeriodic(period, (l) -> bus.send(CuisinierAddress, message));
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
