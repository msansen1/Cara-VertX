package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.enums.CommandeStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.SharedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantVerticle extends AbstractVerticle {

  //La carte du restaurant
  public static ArrayList<String> menu = (ArrayList<String>) Stream.of("La carbonade flamande", "Welsh", "Le chicon-gratin", "Joues de porc au maroilles", "Flamiche au maroilles", "Plat du Jour", "Tajine", "Couscous Royal").collect(Collectors.toList());

  final String serveurAddress ="restaurant.serveur";
  final String clientAddress ="restaurant.client";
  final int period = 3000;
  private int clientId=0;
  final static String clientMessageIntro = "[Client] -";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Restaurant Verticle");
    final EventBus eventbus = vertx.eventBus();

    vertx.setPeriodic(period, (l) -> {
      Client client = new Client(++clientId);
      String address = "address";
      JsonObject jsonToEncode = ClientObjectToJson(client);
      //Json.encode(client)
      eventbus.request(serveurAddress, jsonToEncode, reply->{
        if (reply.succeeded()){
          JsonObject jsonObject = JsonObject.mapFrom(reply.result().body());
          Client c = jsonObject.mapTo(Client.class);
          System.out.println(clientMessageIntro + jsonObject.encode());
          //construire le plat a commander
          //on recupere l'objet client recu'
          c.setPlat(getRandomElement(menu));
          c.setClientStatus(ClientStatus.CLORDERPASSED);
          c.setCommandeStatus(CommandeStatus.CMDORDERED);
          eventbus.request(serveurAddress, ClientObjectToJson(c), replyAr->{
            if (replyAr.succeeded()) {
              JsonObject data2 = JsonObject.mapFrom(reply.result().body());
              System.out.println(clientMessageIntro + data2.encode());
            }
          });
        }
      });
    });

    /*
    final MessageConsumer<String> consumer = eventbus.consumer(clientAddress);
    consumer.handler(message -> {
      System.out.println("[Chef] <- " + message.body());
    });*/
  }

  // Function select an element base on index
  // and return an element
  public String getRandomElement(List<String> list)
  {
    Random rand = new Random();
    return list.get(rand.nextInt(list.size()));
  }

  private JsonObject ClientObjectToJson(Client client) {
    JsonObject jsonToEncode = new JsonObject();
    jsonToEncode.put("id", client.getId());
    jsonToEncode.put("clientStatus", client.getClientStatus());
    jsonToEncode.put("plat", client.getPlat());
    jsonToEncode.put("commandeStatus", client.getCommandeStatus());
    return jsonToEncode;
  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Restaurant Verticle");
  }
}
