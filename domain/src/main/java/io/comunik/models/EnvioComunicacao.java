package io.comunik.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class EnvioComunicacao {
	
	private String id;
	
	@NonNull private LocalDateTime dataHoraEnvio;
	@NonNull private String destinatario;
	@NonNull private String mensagem;
	@NonNull private TipoComunicacao tipoComunicacao;
	private StatusEnvio status;
	
	public void setStatus(StatusEnvio status) {
		this.status = status;
	}
}
