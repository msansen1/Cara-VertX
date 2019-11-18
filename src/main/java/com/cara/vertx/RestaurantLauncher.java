package com.cara.vertx;

import com.cara.vertx.Verticles.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RestaurantLauncher {

  public static int restaurantSize;
  public static int serveursNb;
  public static int clientsNb;
  public static int cuisiniersNb;

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Start of RestaurantLauncher");

    try (InputStream input = RestaurantLauncher.class.getClassLoader().getResourceAsStream("application.properties")) {

      Properties prop = new Properties();

      // load a properties file
      prop.load(input);

      // get the property value and print it out
      restaurantSize= Integer.parseInt(prop.getProperty("restaurant.nbPlaces"));
      serveursNb 	= Integer.parseInt(prop.getProperty("serveurs.nb"));
      clientsNb 	= Integer.parseInt(prop.getProperty("clients.nb"));
      cuisiniersNb = Integer.parseInt(prop.getProperty("cuisiniers.nb"));

    } catch (IOException ex) {
      ex.printStackTrace();
    }

    final Vertx vertx = Vertx.vertx();
    final DeploymentOptions serveurOptions = new DeploymentOptions().setInstances(serveursNb);
    final DeploymentOptions cuisinierOptions = new DeploymentOptions().setInstances(cuisiniersNb);
    final DeploymentOptions ClientOptions = new DeploymentOptions().setInstances(clientsNb);


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
      //vertx.deployVerticle(ClientVerticle.class.getName(),ClientOptions);
    };

    vertx.deployVerticle(RestaurantVerticle.class.getName(),restaurantCompletionHandler);
    vertx.deployVerticle(ServeurVerticle.class.getName(),serverCompletionHandler);

    Thread.sleep(1000);



    System.out.println("End of RestaurantLauncher");

  }
}
