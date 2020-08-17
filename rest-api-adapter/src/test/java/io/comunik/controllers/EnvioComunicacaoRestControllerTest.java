package io.comunik.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.comunik.models.ComunicacaoId;
import io.comunik.models.EnvioComunicacao;
import io.comunik.models.TipoComunicacao;
import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;

@ExtendWith(MockitoExtension.class)
public class EnvioComunicacaoRestControllerTest {
	
	@InjectMocks
	EnvioComunicacaoRestController envioComunicacaoRestController;
	
	@Mock
	AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort;
	
	@Test
	public void addEnvioComunicao_chamaAgendarSolicitacaoEnvioComunicacaoPort() {
		var comunicacaoid = UUID.randomUUID().toString();
		var comunicacaoEntity = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
		
		when(agendarSolicitacaoEnvioComunicacaoPort.agendar(comunicacaoEntity))
			.thenReturn(comunicacaoid);
		
		ResponseEntity<ComunicacaoId> responseEntity = envioComunicacaoRestController.agendarComunicacao(comunicacaoEntity);
		ComunicacaoId statusComunicacao = responseEntity.getBody();
		
		assertTrue(responseEntity.getStatusCode() == HttpStatus.CREATED);
        assertEquals(comunicacaoid, statusComunicacao.id);
        
        verify(agendarSolicitacaoEnvioComunicacaoPort).agendar(comunicacaoEntity);
	}

}
