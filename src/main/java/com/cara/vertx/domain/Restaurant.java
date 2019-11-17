package com.cara.vertx.domain;

public class Restaurant {

  private String name;
  private Integer nbPlaces=10;

  public Restaurant(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getNbPlaces() {
    return nbPlaces;
  }

  public void setNbPlaces(Integer nbPlaces) {
    this.nbPlaces = nbPlaces;
  }
}
