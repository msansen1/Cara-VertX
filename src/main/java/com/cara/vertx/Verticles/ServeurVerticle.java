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


    //periodiquement:
    //solliciter un verticle Client
    //selon son etat, faire avancer son workflow
    vertx.setPeriodic(period, (l) -> {

      getVertx().sharedData().getAsyncMap("clientMap", mapAr-> {
        System.out.println("LOOPING ON MAP");
        mapAr.result().values( var -> var.result().forEach( x -> System.out.println("Key : " + x.toString())));
      });

      eventBus.send(ClientAddress, "Tout va bien ?");
    });

    eventBus.consumer(serveurAddress, res-> {
        // receive a message
        switch(res.body().toString()) {
          case "Une table?":

            vertx
              .sharedData()
              .getLocalCounter(
                "nbPlacesRestaurant",
                resultHandler -> {
                  final Counter counter = resultHandler.result();
                  counter.get( getAr -> {
                    //Long nbPlaces = getAr.result();
                    //System.out.println("COUNTER:" + nbPlaces);
                    if ( getAr.result() > 0 ){
                      counter.decrementAndGet( decAr -> {
                        if (decAr.succeeded()){

                          JsonObject reply = new JsonObject().put("result", "ok");
                          accueilNouveauClient();
                          res.reply(reply);

                          System.out.println("Capacité restante du restaurant: " + decAr.result());
                        }
                      });
                    }else {
                      System.out.println( serveurMessageIntro + " Plus de place");
                    }
                  });


                });

            //JsonObject reply = new JsonObject().put("result", "ok");
            //res.reply(reply);
            break;
          case "Une Biere?":
            JsonObject reply2 = new JsonObject().put("result", "okBiere");
            res.reply(reply2);
            break;
          default:
            // code block
        }




    });



  }

  private void accueilNouveauClient() {
    //ajoute le client a la map
    vertx.sharedData().getLocalAsyncMap("clientMap", addAr -> {
      if (addAr.succeeded()) {
        // Local-only async map
        AsyncMap<Object, Object> map = addAr.result();
        double x = Math.random();
        getVertx().sharedData().getLock("addMap", mar ->
        {
          if (mar.succeeded()) {
            System.out.println("locked"+ mar.result());
            Client c1 = new Client(++clientId);
            map.put(x, Json.encode(c1), resPut -> {
              if (resPut.succeeded()) {
                // Successfully put the value
              } else {
                // Something went wrong!
              }
            });
          }
        });
        Client c1 = new Client(++clientId);
        map.put(x, Json.encode(c1), resPut -> {
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
