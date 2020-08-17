package io.comunik.service;

public class ComunicacaoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ComunicacaoNotFoundException(String id) {
		super("Envio comunicacao com id " + id + " não encontrado");
	}

}
