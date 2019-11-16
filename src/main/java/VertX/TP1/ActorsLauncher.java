package VertX.TP1;

import VertX.TP1.Actors.ChefVerticle;
import VertX.TP1.Actors.CuisinierVerticle;
import VertX.TP1.Actors.ServeurVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class ActorsLauncher {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Start of Actors Launcher");

    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(ChefVerticle.class.getName());
    vertx.deployVerticle(CuisinierVerticle.class.getName());
    /**lancer plusieurs instances de serveurVerticle
    final DeploymentOptions options = new DeploymentOptions()
      .setInstances(5);
     **/
    vertx.deployVerticle(ServeurVerticle.class.getName());

    System.out.println("End of Actors Launcher");

  }
}
