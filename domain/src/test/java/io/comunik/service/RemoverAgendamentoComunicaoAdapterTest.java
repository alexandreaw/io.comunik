package io.comunik.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.StatusEnvio;
import io.comunik.ports.EnvioComunicaoRepository;
import io.comunik.ports.GetAgendamentoComunicaoPort;

@ExtendWith(MockitoExtension.class)
public class RemoverAgendamentoComunicaoAdapterTest {
	
	@Mock
	private EnvioComunicaoRepository envioComunicacaoRepository;
	
	@Mock
	GetAgendamentoComunicaoPort getAgendamentoComunicaoPort;
	
	@InjectMocks
    private RemoverAgendamentoComunicaoAdapter removerAgendamentoComunicaoAdapter;
	
	@Test
	public void deveRemoverAgendamentoSemProblemas() {
		var id = UUID.randomUUID().toString();
		var comunicaca = mock(EnvioComunicacao.class);
		
		when(comunicaca.getStatus())
			.thenReturn(StatusEnvio.AGENDADO);
		when(getAgendamentoComunicaoPort.getAgendamento(id))
			.thenReturn(Optional.of(comunicaca));
		
		removerAgendamentoComunicaoAdapter.remover(id);
		
		verify(envioComunicacaoRepository).deleteById(id);
	}
	
	@Test
	public void deveApresentarErroCasoAgendamentoComunicacaoJaEstiverEnviado() {
		var id = UUID.randomUUID().toString();
		var comunicaca = mock(EnvioComunicacao.class);
		
		when(comunicaca.getStatus())
			.thenReturn(StatusEnvio.ENVIADO);
		when(getAgendamentoComunicaoPort.getAgendamento(id))
			.thenReturn(Optional.of(comunicaca));
		
		InvalidStatusException thrown = assertThrows(
				InvalidStatusException.class,
		           () -> removerAgendamentoComunicaoAdapter.remover(id),
		           "Esperado erro remover agendamento ja enviado"
		    );
		
		assertEquals(thrown.getMessage(), String.format("Envio comunicacao id %s com status invalida %s", id, StatusEnvio.ENVIADO));
	}
	
}
