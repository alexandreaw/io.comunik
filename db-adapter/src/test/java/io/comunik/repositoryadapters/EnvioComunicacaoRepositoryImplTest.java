package io.comunik.repositoryadapters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.EnvioComunicacaoEntity;
import io.comunik.models.TipoComunicacao;

@ExtendWith(MockitoExtension.class)
class EnvioComunicacaoRepositoryImplTest {
	
	@InjectMocks
	EnvioComunicacaoRepositoryImpl envioComunicacaoRepositoryImpl;
	
	@Mock
	private EnvioComunicacaoRepositoryJpa jpa;

	@Test
	void devePersistiEnvioComunicacao() {
		var comunicacao = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
		
		var comunicacaoEntity = mock(EnvioComunicacaoEntity.class);
		
		when(comunicacaoEntity.getId())
			.thenReturn(UUID.randomUUID());
		when(jpa.save(any()))
			.thenReturn(comunicacaoEntity);
		
		envioComunicacaoRepositoryImpl.add(comunicacao);
		
		verify(jpa).save(any());
	}

}
