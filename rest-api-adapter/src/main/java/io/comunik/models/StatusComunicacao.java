package io.comunik.models;

public class StatusComunicacao {
	public StatusComunicacao(String id, StatusEnvio statusEnvio) {
		this.id = id;
		this.status = statusEnvio;
	}
	public String id;
	public StatusEnvio status;
}
