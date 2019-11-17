package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;

public class ServeurVerticle extends AbstractVerticle {

  final String message="[Serveur]:j'ai une commande";
  final String chefAddress="restaurant.chef";
  final String serveurAddress="restaurant.serveur";
  final String CuisinierAddress="restaurant.cuisinier";
  final String ClientAddress="restaurant.client";

  final int period = 3000;

  final static String serveurMessageIntro = "[Serveur] - ";

  private int clientId=0;

  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
    final EventBus eventBus = vertx.eventBus();

    eventBus.consumer(serveurAddress, res -> {
      //receive a message
      JsonObject jsonObject = JsonObject.mapFrom(res.body());
      Client client = jsonObject.mapTo(Client.class);
      System.out.println(client.toString());
      JsonObject reply = new JsonObject().put("result", "ok");
      res.reply(reply);


    });
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
