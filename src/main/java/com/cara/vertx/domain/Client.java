package com.cara.vertx.domain;

import com.cara.vertx.enums.ClientStatus;
import com.cara.vertx.enums.CommandeStatus;
import com.cara.vertx.messages.CommandeClient;

public class Client {

  private int id;
  private int cs;
  private int cc;

  public Client() {
    this.id = 1;
    this.cs = ClientStatus.CLPLACED;
    this.cc = CommandeStatus.CMDORDERING;
  }

  public Client(ClientStatus cs, CommandeClient cc) {
    this.cs = ClientStatus.CLPLACED;
    this.cc = CommandeStatus.CMDORDERING;
  }

  public int getClientStatus() {
    return cs;
  }

  public void setClientStatus(int cs) {
    this.cs = cs;
  }

  public int getCommandeClient() {
    return cc;
  }

  public void setCommandeClient(int cc) {
    this.cc = cc;
  }
}
