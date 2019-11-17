package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.AsyncMap;

public class ServeurVerticle extends AbstractVerticle {

  final String message="[Serveur]:j'ai une commande";
  final String chefAddress="restaurant.chef";
  final String CuisinierAddress="Cuisinier.address";

  final int period = 3000;

  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
    final EventBus bus = vertx.eventBus();
    bus.send(chefAddress,message);
    vertx.setPeriodic(period, (l) -> {
      bus.send(CuisinierAddress, message);

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
      System.out.println("client pushed");


    });
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
