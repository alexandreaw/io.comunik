package io.comunik.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.comunik.models.EnvioComunicacao;
import io.comunik.ports.EnvioComunicaoRepository;

@ExtendWith(MockitoExtension.class)
public class GetAgendamentoComunicaoAdapterTest {
	
	@InjectMocks
	GetAgendamentoComunicaoAdapter getStatusAgendamentoAdapter;
	
	@Mock
	EnvioComunicaoRepository repository;
	
	@Test
	public void deveApresentarExceptionQuandoIdAgendamentoNaoExistir() {
		var id = UUID.randomUUID();
		
		when(repository.findById(id.toString()))
			.thenReturn(Optional.empty());
		
		ComunicacaoNotFoundException thrown = assertThrows(
				ComunicacaoNotFoundException.class,
		           () -> getStatusAgendamentoAdapter.getAgendamento(id.toString()),
		           "Esperado erro ao consultar agendamento inexistente"
		    );
		
		assertEquals(thrown.getMessage(), "Envio comunicacao com id "+ id.toString() +" não encontrado");
	}
	
	@Test
	public void deveRetornarEnvioComunicacaoQuandoIdExistir() {
		var id = UUID.randomUUID();
		
		var envioMock = mock(EnvioComunicacao.class);
		
		when(repository.findById(id.toString()))
			.thenReturn(Optional.of(envioMock));
		
		Optional<EnvioComunicacao> envioComunicacao = getStatusAgendamentoAdapter.getAgendamento(id.toString());
		
		assertTrue(envioComunicacao.isPresent());
	}
}
