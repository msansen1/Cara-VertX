package com.cara.vertx.domain;

import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.messages.CommandeClient;

public class Client {

  private Integer id;
  private ClientStatus cs;
  private CommandeClient cc;

  public ClientStatus getClientStatus() {
    return cs;
  }

  public void setClientStatus(ClientStatus cs) {
    this.cs = cs;
  }

  public CommandeClient getCommandeClient() {
    return cc;
  }

  public void setCommandeClient(CommandeClient cc) {
    this.cc = cc;
  }
}
