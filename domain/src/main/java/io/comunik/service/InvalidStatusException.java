package io.comunik.service;

import io.comunik.models.StatusEnvio;

public class InvalidStatusException extends RuntimeException {

	public InvalidStatusException(String id, StatusEnvio status) {
		super(String.format("Envio comunicacao id %s com status invalida %s", id, status));
	}

	private static final long serialVersionUID = 1L;

}
