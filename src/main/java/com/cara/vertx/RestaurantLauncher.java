package com.cara.vertx;

import com.cara.vertx.Verticles.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.shareddata.Counter;
import io.vertx.core.shareddata.SharedData;

public class RestaurantLauncher {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Start of RestaurantLauncher");

    final Vertx vertx = Vertx.vertx();
    final DeploymentOptions serveurOptions = new DeploymentOptions().setInstances(3);
    final DeploymentOptions cuisinierOptions = new DeploymentOptions().setInstances(2);
    final DeploymentOptions ClientOptions = new DeploymentOptions().setInstances(10);
    final DeploymentOptions PlacesOptions = new DeploymentOptions().setInstances(5);

    SharedData sharedData = vertx.sharedData();

    vertx
        .sharedData()
        .getCounter(
          "nbPlacesRestaurant",
          resultHandler -> {
            final Counter counter = resultHandler.result();
            counter.get(
              handler -> {
                long val = handler.result();
                if (val == 0) {
                  counter.addAndGet(
                    10,
                    hhh -> {
                      System.out.println("nbPlacesRestaurant:" + hhh.result());
                    });
                } else {

                }
              });
          });

    Thread.sleep(1000);
    sharedData.getCounter("nbPlacesRestaurant", res -> {
      if (res.succeeded()) {
        Counter counter = res.result();
        System.out.println("nbPlacesRestaurant:" + counter);
      } else {
        // Something went wrong!
      }
    });


    final Handler<AsyncResult<String>> restaurantCompletionHandler = ar -> {
      System.out.println("Restaurant Verticle Deployed");
      //Chef
      vertx.deployVerticle(ChefVerticle.class.getName());
      //Cuisinier
      vertx.deployVerticle(CuisinierVerticle.class.getName(),cuisinierOptions);
      //Serveur
      vertx.deployVerticle(ServeurVerticle.class.getName(),serveurOptions);

    };

    final Handler<AsyncResult<String>> serverCompletionHandler = ar -> {
      //si tout le personnel du restaurant est prêt, on ouvre aux clients
      //Clients
      vertx.deployVerticle(ClientVerticle.class.getName(),ClientOptions);
    };

    vertx.deployVerticle(RestaurantVerticle.class.getName(),restaurantCompletionHandler);
    vertx.deployVerticle(ServeurVerticle.class.getName(),serverCompletionHandler);

    System.out.println("End of RestaurantLauncher");

  }
}
