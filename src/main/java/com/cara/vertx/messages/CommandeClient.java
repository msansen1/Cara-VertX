package com.cara.vertx.messages;

import com.cara.vertx.domain.Client;
import com.cara.vertx.enums.CommandeStatus;

import java.io.Serializable;

public class CommandeClient implements Serializable {

  private String plat = "";
  private int status;

  public CommandeClient() {
    this.status = CommandeStatus.CMDORDERING;
  }

  public CommandeClient(String plat) {
    this.plat = plat;
    this.status = CommandeStatus.CMDORDERED;
  }

  public void setPlat(String plat) {
    this.plat = plat;
  }

  public String getPlat() {
    return plat;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "CommandeClient{" +
      "plat='" + plat + '\'' +
      ", status=" + status +
      '}';
  }
}
