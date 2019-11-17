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

  }

  @Override
  public void stop() throws Exception {
    System.out.println("Stop of Restaurant Verticle");
  }
}
