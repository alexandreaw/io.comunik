package io.comunik.ports;

import java.util.Optional;

import io.comunik.models.EnvioComunicacao;

public interface EnvioComunicaoRepository {

	String add(EnvioComunicacao comunicacao);

	Optional<EnvioComunicacao> findById(String id);

	void deleteById(String id);

}
