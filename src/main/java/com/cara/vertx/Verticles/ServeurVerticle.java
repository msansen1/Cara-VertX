package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.AsyncMap;

public class ServeurVerticle extends AbstractVerticle {

  final String message="[Serveur]:j'ai une commande";
  final String chefAddress="restaurant.chef";
  final String clientAddress="restaurant.client";
  final String CuisinierAddress="Cuisinier.address";

  final int period = 3000;

  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
    final EventBus bus = vertx.eventBus();
    bus.send(chefAddress,message);
    vertx.setPeriodic(period, (l) -> {
      bus.send(CuisinierAddress, message);
    });

    final MessageConsumer<String> consumer = bus.consumer(clientAddress);
    consumer.handler(message -> {
      System.out.println("[Serveur] <- " + message.body());
      //TODO verifier capacité du restaurant
      accueilNouveauClient();
    });

  }

  private void accueilNouveauClient() {
    //ajoute le client a la map
    vertx.sharedData().getAsyncMap("clientMap", res -> {
      if (res.succeeded()) {
        // Local-only async map
        AsyncMap<Object, Object> map = res.result();
        Client c1 = new Client();
        map.put("1", Json.encode(c1), resPut -> {
          if (resPut.succeeded()) {
            // Successfully put the value
          } else {
            // Something went wrong!
          }
        });
      } else {
        // Something went wrong!
      }
    });
    System.out.println("client pushed to MAP");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
