package io.comunik.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;
import io.comunik.ports.EnvioComunicaoRepository;
import io.comunik.service.AgendarSolicitacaoEnvioComunicacaoAdapter;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
    public AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort(EnvioComunicaoRepository envioComunicaoRepository) {
        return new AgendarSolicitacaoEnvioComunicacaoAdapter(envioComunicaoRepository);
    }

}
