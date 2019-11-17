package com.cara.vertx.domain;

import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.enums.CommandeStatus;
import com.cara.vertx.messages.CommandeClient;

import java.io.Serializable;

public class Client implements Serializable {

  private int id;
  private int clientStatus;
  private CommandeClient commandeClient;

  public Client() {
    this.id = 1;
    this.clientStatus = ClientStatus.CLPLACED;
    this.commandeClient = new CommandeClient();
  }
/*
  public Client(ClientStatus clientStatus, CommandeClient commandeStatus) {
    this.clientStatus = ClientStatus.CLPLACED;
  }*/

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getClientStatus() {
    return clientStatus;
  }

  public void setClientStatus(int clientStatus) {
    this.clientStatus = clientStatus;
  }

  public CommandeClient getCommandeClient() {
    return commandeClient;
  }

  public void setCommandeClient(CommandeClient commandeClient) {
    this.commandeClient = commandeClient;
  }
}
