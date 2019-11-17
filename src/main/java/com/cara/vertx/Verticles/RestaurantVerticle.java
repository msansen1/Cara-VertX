package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.SharedData;

public class RestaurantVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("Start of Restaurant Verticle");

    //On définit la capacité du restaurant dans un compteur
    vertx.sharedData().getCounter(
        "nbPlacesRestaurant",
        ar -> {
          if (ar.succeeded()){
            Counter counter = ar.result();
            counter.addAndGet(10,opAr -> {
              if(opAr.succeeded()){
                System.out.println("[succeeded] NBRPLACES:"+opAr.result());
              }else {
                System.out.println(opAr.cause());
              }
            });
          }else {
            System.out.println(ar.cause());
          }
        });

    SharedData sharedData = vertx.sharedData();

    sharedData.<Integer, String>getAsyncMap("clientMap", res -> {
      if (res.succeeded()) {
        AsyncMap<Integer, String> map = res.result();
      } else {
        System.out.println(res.cause());
      }
    });

  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Restaurant Verticle");
  }
}
