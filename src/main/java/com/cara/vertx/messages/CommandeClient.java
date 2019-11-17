package com.cara.vertx.messages;

import com.cara.vertx.enums.CommandeStatus;

import java.io.Serializable;

public class CommandeClient implements Serializable {

	private String plat="";
	private int quantity =1;
	//private ActorRef client;
	private int status;

    public CommandeClient() {
        this.status = CommandeStatus.CMDORDERING;
    }

    public CommandeClient(String inWord, Integer inCount) {
		plat = inWord;
		quantity = inCount;
	}

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getPlat() {
		return plat;
	}

	public Integer getQuantity() {
		return quantity;
	}

	/*
	public ActorRef getClient() {
		return client;
	}

	public void setClient(ActorRef client) {
		this.client = client;
	}*/

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
				", quantity=" + quantity +
				//", client=" + client +
				", status=" + status +
				'}';
	}
}
