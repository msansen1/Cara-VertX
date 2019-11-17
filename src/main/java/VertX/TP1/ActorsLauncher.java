package VertX.TP1;

import VertX.TP1.Actors.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class ActorsLauncher {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Start of Actors Launcher");

    final Vertx vertx = Vertx.vertx();
    final DeploymentOptions serveurOptions = new DeploymentOptions().setInstances(3);
    final DeploymentOptions cuisinierOptions = new DeploymentOptions().setInstances(2);
    final DeploymentOptions ClientOptions = new DeploymentOptions().setInstances(30);


    final Handler<AsyncResult<String>> restaurantCompletionHandler = sar -> {
      System.out.println("Restaurant Verticle Deployed");
      //Chef
      vertx.deployVerticle(ChefVerticle.class.getName());
      //Clients
      vertx.deployVerticle(ClientVerticle.class.getName(),ClientOptions);

    };

    final Handler<AsyncResult<String>> ChefCompletionHandler = sar -> {
      //Serveur
      vertx.deployVerticle(ServeurVerticle.class.getName(),serveurOptions);

      //Cuisinier
      vertx.deployVerticle(CuisinierVerticle.class.getName(),cuisinierOptions);

    };

    vertx.deployVerticle(ChefVerticle.class.getName(),ChefCompletionHandler);
    vertx.deployVerticle(RestaurantVerticle.class.getName(),restaurantCompletionHandler);

    System.out.println("End of Actors Launcher");

  }
}
