package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.enums.CommandeStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Random;

public class ClientVerticle extends AbstractVerticle {



  final int period = 3000;
  private int clientId=0;
  final static String clientMessageIntro = "[Client] -";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Client Verticle");

    final EventBus eventbus = vertx.eventBus();


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
