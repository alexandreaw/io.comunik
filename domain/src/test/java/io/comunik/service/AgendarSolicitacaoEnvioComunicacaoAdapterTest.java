package io.comunik.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.StatusEnvio;
import io.comunik.models.TipoComunicacao;
import io.comunik.ports.EnvioComunicaoRepository;

@ExtendWith(MockitoExtension.class)
public class AgendarSolicitacaoEnvioComunicacaoAdapterTest {
	
	@Mock
	private EnvioComunicaoRepository envioComunicacaoRepository;
	
	@InjectMocks
    private AgendarSolicitacaoEnvioComunicacaoAdapter agendarSolicitacaoEnvioComunicacaoAdapter;

	@Test
	public void deveAdicionarEnvioComunicacaoSemProblemas() {
		var comunicacao = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
		
		agendarSolicitacaoEnvioComunicacaoAdapter.agendar(comunicacao);
		
		verify(envioComunicacaoRepository).add(comunicacao);
	}
	
	@Test
	public void deveAdicionarEnvioComunicacaoEoStatusDeveSempreSerAgendado() {
		var comunicacao = mock(EnvioComunicacao.class);
		when(comunicacao.getDataHoraEnvio()).thenReturn(LocalDateTime.now().plusHours(1));
		
		agendarSolicitacaoEnvioComunicacaoAdapter.agendar(comunicacao);
		
		verify(comunicacao).setStatus(StatusEnvio.AGENDADO);
	}
	
	@Test
	public void dataHoraInformadaNaoDeveSerMenorQueAtual() {
		LocalDateTime dataHoraEnvio = LocalDateTime.of(2020, Month.APRIL, 5, 9, 33);
		
		var comunicacao = EnvioComunicacao.builder()
				.dataHoraEnvio(dataHoraEnvio)
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.WHATSAPP)
				.build();
		
		DataHoraInvalidException thrown = assertThrows(
				DataHoraInvalidException.class,
		           () -> agendarSolicitacaoEnvioComunicacaoAdapter.agendar(comunicacao),
		           "Esperada exception caso data seja menor que atual"
		    );
		
		assertEquals(thrown.getMessage(), "DataHora "+ dataHoraEnvio +" invalida ou menor que datahora atual");
	}
}
