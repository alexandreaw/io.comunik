package io.comunik.mappers;

import java.util.UUID;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.EnvioComunicacaoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnvioComunicacaoRepositoryMapper {
	
	public EnvioComunicacaoEntity toDatabaseEntity(final EnvioComunicacao envioComunicacao) {
        return EnvioComunicacaoEntity.builder()
                .dataHoraEnvio(envioComunicacao.getDataHoraEnvio())
                .destinatario(envioComunicacao.getDestinatario())
                .mensagem(envioComunicacao.getMensagem())
                .status(envioComunicacao.getStatus())
                .tipoComunicacao(envioComunicacao.getTipoComunicacao())
                .id(envioComunicacao.getId() == null ? null : UUID.fromString(envioComunicacao.getId()))
                .build();
    }
	
	public EnvioComunicacao toDomainEntity(final EnvioComunicacaoEntity envioComunicacaoEntity) {
        return EnvioComunicacao.builder()
		        .dataHoraEnvio(envioComunicacaoEntity.getDataHoraEnvio())
		        .destinatario(envioComunicacaoEntity.getDestinatario())
		        .mensagem(envioComunicacaoEntity.getMensagem())
		        .status(envioComunicacaoEntity.getStatus())
		        .tipoComunicacao(envioComunicacaoEntity.getTipoComunicacao())
		        .id(envioComunicacaoEntity.getId() == null ? null : envioComunicacaoEntity.getId().toString())
		        .build();
    }
	
}
