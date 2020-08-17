package io.comunik.models;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class EnvioComunicacao {
	
	@ApiModelProperty(required = false, hidden = true)
	private String id;
	
	@NonNull private LocalDateTime dataHoraEnvio;
	@NonNull private String destinatario;
	@NonNull private String mensagem;
	@ApiModelProperty(value = "Tipo de comunicação", allowableValues = "EMAIL,SMS,PUSH,WHATSAPP") @NonNull private TipoComunicacao tipoComunicacao;
	@ApiModelProperty(required = false, hidden = true) private StatusEnvio status;
	
	public void setStatus(StatusEnvio status) {
		this.status = status;
	}
}
