package io.comunik.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.EnvioComunicacaoEntity;
import io.comunik.models.TipoComunicacao;

public class EnvioComunicacaoRepositoryMapperTest {
	
	@Test
	public void deveRetornaUmaEntidadeParaDataBase() {
		var dataBaseDomain = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
		
		var dataBaseEntity = EnvioComunicacaoRepositoryMapper.toDatabaseEntity(dataBaseDomain);
		
		assertNotNull(dataBaseEntity);
		assertEquals(dataBaseDomain.getDataHoraEnvio(), dataBaseEntity.getDataHoraEnvio());
		assertEquals(dataBaseDomain.getDestinatario(), dataBaseEntity.getDestinatario());
		assertEquals(dataBaseDomain.getMensagem(), dataBaseEntity.getMensagem());
		assertEquals(dataBaseDomain.getTipoComunicacao(), dataBaseEntity.getTipoComunicacao());
	}
	
	@Test
	public void deveRetornaUmaEntidadeParaDomain() {
		var dataBaseEntity = EnvioComunicacaoEntity.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
		
		var domainEntity = EnvioComunicacaoRepositoryMapper.toDomainEntity(dataBaseEntity);
		
		assertNotNull(domainEntity);
		assertEquals(dataBaseEntity.getDataHoraEnvio(), domainEntity.getDataHoraEnvio());
		assertEquals(dataBaseEntity.getDestinatario(), domainEntity.getDestinatario());
		assertEquals(dataBaseEntity.getMensagem(), domainEntity.getMensagem());
		assertEquals(dataBaseEntity.getTipoComunicacao(), domainEntity.getTipoComunicacao());
	}
	
}
