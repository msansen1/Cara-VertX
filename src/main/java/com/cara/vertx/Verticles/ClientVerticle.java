package com.cara.vertx.Verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class ClientVerticle extends AbstractVerticle {


  final String serveurAddress ="restaurant.serveur";

  //final int period = 3000;

  final static String clientMessageIntro = "[Client] -";

  @Override
  public void start() throws Exception {
    System.out.println("Start of Client Verticle");

    final EventBus eventbus = vertx.eventBus();
    eventbus.request(serveurAddress,"Une table?", reply -> {
      if ( reply.succeeded()){
        System.out.println( clientMessageIntro + " < " + reply.result().body() );
        //JsonObject data = JsonObject.mapFrom(reply.result());
        //System.out.println(data.encode());
      }
    });

    /*
    vertx.setPeriodic(period, (l) -> {
      eventbus.send(serveurAddress,"UpdateMe");
    });*/


  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Client Verticle");
  }
}
