package io.comunik.service;

import java.time.LocalDateTime;

import lombok.NonNull;

public class DataHoraInvalidException extends RuntimeException {

	public DataHoraInvalidException(@NonNull LocalDateTime dataHoraEnvio) {
		super("DataHora "+ dataHoraEnvio +" invalida ou menor que datahora atual");
	}

	private static final long serialVersionUID = 1L;

	
}
