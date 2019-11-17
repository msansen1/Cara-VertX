package com.cara.vertx.Verticles;

import com.cara.vertx.domain.Client;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.SharedData;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantVerticle extends AbstractVerticle {

  //La carte du restaurant
  public static ArrayList<String> menu = (ArrayList<String>) Stream.of("La carbonade flamande", "Welsh", "Le chicon-gratin", "Joues de porc au maroilles", "Flamiche au maroilles", "Plat du Jour", "Tajine", "Couscous Royal").collect(Collectors.toList());

  @Override
  public void start() throws Exception {
    System.out.println("Start of Restaurant Verticle");

    //TODO recuperer la capacité du restaurant depuis le Launcher
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

    vertx.sharedData().getCounter(
      "capacitéRestaurant",
      ar2 -> {
        if (ar2.succeeded()){
          Counter counter = ar2.result();
          counter.addAndGet(10,opAr -> {
            if(opAr.succeeded()){
              System.out.println("[succeeded] NBRPLACES:"+opAr.result());
            }else {
              System.out.println(opAr.cause());
            }
          });
        }else {
          System.out.println(ar2.cause());
        }
      });

    vertx.sharedData().<Integer, String>getAsyncMap("clientMap", res -> {
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
