package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.enums.CommandeStatus;
import com.cara.vertx.utils;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

import static com.cara.vertx.Verticles.RestaurantVerticle.ClientObjectToJson;
import static com.cara.vertx.Verticles.RestaurantVerticle.getRandomElement;

public class CuisinierVerticle extends AbstractVerticle {

  final String serveurAddress="restaurant.serveur";
  final String CuisinierAddress="restaurant.cuisinier";

  final static String messageIntro = "[Cuisinier] - ";

  @Override
  public void start() throws Exception {
    System.out.println(messageIntro + "Start of Cuisinier Verticle");

    //Recevoir un message
    final EventBus eventBus = vertx.eventBus();
    final MessageConsumer<JsonObject> consumer = eventBus.consumer(CuisinierAddress);
    /**
     * Q2.(Recevoir le message de Cuisinier)
     * */

    /**

   });
   * */


  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Cuisinier Verticle");
  }
}
