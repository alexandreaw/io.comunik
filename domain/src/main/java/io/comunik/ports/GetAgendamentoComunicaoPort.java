package io.comunik.ports;

import java.util.Optional;

import io.comunik.models.EnvioComunicacao;

public interface GetAgendamentoComunicaoPort {

	Optional<EnvioComunicacao> getAgendamento(String id);

}
