package io.comunik.models;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class EnvioComunicacao {
	
	@ApiModelProperty(required = false, hidden = true)
	private String id;
	
	@NotNull(message = "Data envio não pode ser vazio")
	private LocalDateTime dataHoraEnvio;
	
	@NotEmpty(message = "Destinatario não pode ser vazio")
	private String destinatario;
	
	@NotEmpty(message = "Mensagem não pode ser vazio") 
	private String mensagem;
	
	@ApiModelProperty(value = "Tipo de comunicação", allowableValues = "EMAIL,SMS,PUSH,WHATSAPP") @NonNull @NotNull(message = "Tipo comunicacao não pode ser vazio")
	private TipoComunicacao tipoComunicacao;
	
	@ApiModelProperty(required = false, hidden = true) private 
	StatusEnvio status;
	
	public void setStatus(StatusEnvio status) {
		this.status = status;
	}
}
