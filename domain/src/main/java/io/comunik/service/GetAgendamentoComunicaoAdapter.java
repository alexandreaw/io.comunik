package io.comunik.service;

import java.util.Optional;

import io.comunik.models.EnvioComunicacao;
import io.comunik.ports.EnvioComunicaoRepository;
import io.comunik.ports.GetAgendamentoComunicaoPort;

public class GetAgendamentoComunicaoAdapter implements GetAgendamentoComunicaoPort {

	EnvioComunicaoRepository repository;
	
	public GetAgendamentoComunicaoAdapter(EnvioComunicaoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Optional<EnvioComunicacao> getAgendamento(String id) {
		Optional<EnvioComunicacao> envioComunicacao = repository.findById(id);
		if(envioComunicacao.isEmpty()) {
			throw new ComunicacaoNotFoundException(id);
		}
		
		return envioComunicacao;
	}

}
