package io.comunik.service;

import java.time.LocalDateTime;

public class DataHoraInvalidException extends RuntimeException {

	public DataHoraInvalidException(LocalDateTime dataHoraEnvio) {
		super("DataHora "+ dataHoraEnvio +" invalida ou menor que datahora atual");
	}

	private static final long serialVersionUID = 1L;

	
}
