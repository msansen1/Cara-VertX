package VertX.TP1;

import VertX.TP1.Actors.ChefVerticle;
import VertX.TP1.Actors.CuisinierVerticle;
import VertX.TP1.Actors.RestaurantVerticle;
import VertX.TP1.Actors.ServeurVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class ActorsLauncher {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Start of Actors Launcher");

    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(RestaurantVerticle.class.getName(), ar -> {
      System.out.println("Restaurant Verticle Deployed");

      //Chef
      vertx.deployVerticle(ChefVerticle.class.getName());

      //Serveur
      final DeploymentOptions serveurOptions = new DeploymentOptions().setInstances(3);
      vertx.deployVerticle(ServeurVerticle.class.getName(),serveurOptions);

      //Cuisinier
      final DeploymentOptions cuisinierOptions = new DeploymentOptions().setInstances(2);
      vertx.deployVerticle(CuisinierVerticle.class.getName(),cuisinierOptions);

    });

    System.out.println("End of Actors Launcher");

  }
}
