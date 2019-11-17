package com.cara.vertx.messages;

import java.io.Serializable;

public class EchangeClient implements Serializable {

	private String text;
	private int status;
	//private Client client;


	public EchangeClient(String text, int status) {
		this.text = text;
		this.status = status;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/*public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}*/

	@Override
	public String toString() {
		return "EchangeClient{" +
				"text='" + text + '\'' +
				", status=" + status +
				//", client=" + client +
				'}';
	}
}
