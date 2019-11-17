package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Random;

public class ClientVerticle extends AbstractVerticle {


  final String serveurAddress ="restaurant.serveur";
  final String clientAddress ="restaurant.client";

  final int period = 3000;
  private int clientId=0;
  final static String clientMessageIntro = "[Client] -";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Client Verticle");

    final EventBus eventbus = vertx.eventBus();


    vertx.setPeriodic(period, (l) -> {
      Client client = new Client(++clientId);
      String address = "address";
      JsonObject jsonToEncode = new JsonObject();
      jsonToEncode.put("id", client.getId());
      jsonToEncode.put("clientStatus", client.getClientStatus());
      jsonToEncode.put("plat", client.getPlat());
      jsonToEncode.put("commandeStatus", client.getCommandeStatus());
      //Json.encode(client)
      eventbus.request(serveurAddress, jsonToEncode, reply->{
        if (reply.succeeded()){
          JsonObject data = JsonObject.mapFrom(reply.result().body());
          System.out.println(clientMessageIntro + data.encode());
        }
      });
    });


    final MessageConsumer<String> consumer = eventbus.consumer(clientAddress);
    consumer.handler(message -> {
      System.out.println("[Chef] <- " + message.body());
    });


  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Client Verticle");
  }

  // Function select an element base on index
  // and return an element
  public String getRandomElement(List<String> list)
  {
    Random rand = new Random();
    return list.get(rand.nextInt(list.size()));
  }
}
