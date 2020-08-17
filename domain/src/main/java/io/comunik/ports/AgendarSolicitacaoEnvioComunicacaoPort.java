package io.comunik.ports;

import io.comunik.models.EnvioComunicacao;

public interface AgendarSolicitacaoEnvioComunicacaoPort {
	
	String agendar(EnvioComunicacao comunicacao);
	
}
