package io.comunik.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;
import io.comunik.ports.EnvioComunicaoRepository;
import io.comunik.ports.GetAgendamentoComunicaoPort;
import io.comunik.ports.RemoverAgendamentoComunicaoPort;
import io.comunik.service.AgendarSolicitacaoEnvioComunicacaoAdapter;
import io.comunik.service.GetAgendamentoComunicaoAdapter;
import io.comunik.service.RemoverAgendamentoComunicaoAdapter;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
    public AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort(EnvioComunicaoRepository envioComunicaoRepository) {
        return new AgendarSolicitacaoEnvioComunicacaoAdapter(envioComunicaoRepository);
    }
	
	@Bean
    public GetAgendamentoComunicaoPort getStatusAgendamentoPort(EnvioComunicaoRepository envioComunicaoRepository) {
        return new GetAgendamentoComunicaoAdapter(envioComunicaoRepository);
    }
	
	@Bean
    public RemoverAgendamentoComunicaoPort removerAgendamentoComunicaoPort(EnvioComunicaoRepository envioComunicaoRepository, GetAgendamentoComunicaoPort getAgendamentoComunicaoPort) {
        return new RemoverAgendamentoComunicaoAdapter(envioComunicaoRepository, getAgendamentoComunicaoPort);
    }

}
