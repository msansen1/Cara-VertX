package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.AsyncMap;

public class ServeurVerticle extends AbstractVerticle {

  final String message="[Serveur]:j'ai une commande";
  final String chefAddress="restaurant.chef";
  final String serveurAddress="restaurant.serveur";
  final String CuisinierAddress="restaurant.cuisinier";
  final String ClientAddress="restaurant.client";

  final int period = 3000;

  final static String serveurMessageIntro = "[Serveur] - ";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Serveur Verticle");
    final EventBus eventBus = vertx.eventBus();


    //eventBus.send(chefAddress,message);

    vertx.setPeriodic(period, (l) -> {
      eventBus.send(ClientAddress, "Tout va bien ?");
    });
/*
    final MessageConsumer<String> consumer = eventBus.consumer(serveurAddress);
    consumer.handler(message -> {
      System.out.println(serveurMessageIntro + message.body());
      //TODO verifier capacité du restaurant
      accueilNouveauClient();
    });*/
/*
    eventBus.consumer(serveurAddress, msg -> {
      msg.replyAndRequest("Une table?", response -> {
        eventBus.send()
      });
    });*/

    eventBus.consumer(serveurAddress, res->{
      // receive a message
      //JsonObject payload = JsonObject.mapFrom(res.body());
      //JsonObject reply = new JsonObject().put("result", "ok");
      //TODO verifier capacité du restaurant
      //if ()

      String reply = serveurMessageIntro + "> Oui";
      accueilNouveauClient();
      res.replyAndRequest(reply, res2->{
        if (res2.succeeded()){
          System.out.println( serveurMessageIntro + " reply was received...");
        } else {
          System.out.println( serveurMessageIntro + " reply failed...");
        }
      });
    });

    //periodiquement:
    //solliciter un verticle Client
      //selon son etat, faire avancer son workflow

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
    System.out.println( serveurMessageIntro + "client pushed to MAP");
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Serveur Verticle");
  }
}
