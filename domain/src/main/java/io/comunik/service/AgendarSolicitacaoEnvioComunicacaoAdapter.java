package io.comunik.service;

import java.time.LocalDateTime;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.StatusEnvio;
import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;
import io.comunik.ports.EnvioComunicaoRepository;

public class AgendarSolicitacaoEnvioComunicacaoAdapter implements AgendarSolicitacaoEnvioComunicacaoPort {

	EnvioComunicaoRepository envioComunicacaoRepository;
	
	public AgendarSolicitacaoEnvioComunicacaoAdapter(EnvioComunicaoRepository envioComunicacaoRepository) {
		super();
		this.envioComunicacaoRepository = envioComunicacaoRepository;
	}

	@Override
	public String agendar(EnvioComunicacao comunicacao) {
		if(comunicacao.getDataHoraEnvio().isBefore(LocalDateTime.now())) {
			throw new DataHoraInvalidException(comunicacao.getDataHoraEnvio());
		}
		
		comunicacao.setStatus(StatusEnvio.AGENDADO);
		return envioComunicacaoRepository.add(comunicacao);
	}

}
