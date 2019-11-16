package VertX.TP1;

import VertX.TP1.Actors.ChefVerticle;
import VertX.TP1.Actors.CuisinierVerticle;
import VertX.TP1.Actors.ServeurVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class ActorsLauncher {

  public static void main(String[] args) {
    System.out.println("Start of Actors Launcher");

    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(CuisinierVerticle.class.getName());
    vertx.deployVerticle(ChefVerticle.class.getName());
    //lancer plusieurs instances de serveurVerticle
    final DeploymentOptions options = new DeploymentOptions() //
      .setInstances(5);
    vertx.deployVerticle(ServeurVerticle.class.getName(), options);

    System.out.println("End of Actors Launcher");

  }
}
