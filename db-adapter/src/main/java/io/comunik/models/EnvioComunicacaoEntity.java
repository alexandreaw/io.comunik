package io.comunik.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "envio_comunicacao")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class EnvioComunicacaoEntity {
	
		@Id @Type(type="pg-uuid") @GeneratedValue(strategy = GenerationType.AUTO)
		private UUID id;
		@NonNull @Column(name = "data_hora_envio") LocalDateTime dataHoraEnvio;
		@NonNull private String destinatario;
		@NonNull private String mensagem;
		@NonNull @Column(name = "tipo_comunicacao")  private TipoComunicacao tipoComunicacao;
		@Column(name = "status_envio") private StatusEnvio status;
}
