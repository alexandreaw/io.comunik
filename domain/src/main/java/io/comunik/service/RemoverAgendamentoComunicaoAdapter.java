package io.comunik.service;

import java.util.Optional;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.StatusEnvio;
import io.comunik.ports.EnvioComunicaoRepository;
import io.comunik.ports.GetAgendamentoComunicaoPort;
import io.comunik.ports.RemoverAgendamentoComunicaoPort;

public class RemoverAgendamentoComunicaoAdapter implements RemoverAgendamentoComunicaoPort {
	
	EnvioComunicaoRepository envioComunicacaoRepository;
	GetAgendamentoComunicaoPort agendamentoComunicaoPort;
	
	public RemoverAgendamentoComunicaoAdapter(EnvioComunicaoRepository envioComunicacaoRepository,
			GetAgendamentoComunicaoPort agendamentoComunicaoPort) {
		super();
		this.envioComunicacaoRepository = envioComunicacaoRepository;
		this.agendamentoComunicaoPort = agendamentoComunicaoPort;
	}

	@Override
	public void remover(String id) {
		Optional<EnvioComunicacao> agendamento = agendamentoComunicaoPort.getAgendamento(id);
		
		var envioComunicacao = agendamento.get();
		if(envioComunicacao.getStatus().equals(StatusEnvio.ENVIADO)) {
			throw new InvalidStatusException(id, envioComunicacao.getStatus());
		}
		
		envioComunicacaoRepository.deleteById(id);
	}

}
