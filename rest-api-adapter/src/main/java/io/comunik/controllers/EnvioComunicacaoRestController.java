package io.comunik.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.comunik.models.ComunicacaoId;
import io.comunik.models.EnvioComunicacao;
import io.comunik.models.StatusComunicacao;
import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;
import io.comunik.ports.GetAgendamentoComunicaoPort;
import io.comunik.ports.RemoverAgendamentoComunicaoPort;

@RestController
public class EnvioComunicacaoRestController {
	
	private AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort;
	private GetAgendamentoComunicaoPort getStatusAgendamentoPort;
	private RemoverAgendamentoComunicaoPort removerAgendamentoComunicaoPort;
	
	@Autowired
	public EnvioComunicacaoRestController(AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort,
			GetAgendamentoComunicaoPort getStatusAgendamentoPort,
			RemoverAgendamentoComunicaoPort removerAgendamentoComunicaoPort) {
		super();
		this.agendarSolicitacaoEnvioComunicacaoPort = agendarSolicitacaoEnvioComunicacaoPort;
		this.getStatusAgendamentoPort = getStatusAgendamentoPort;
		this.removerAgendamentoComunicaoPort = removerAgendamentoComunicaoPort;
	}

	@RequestMapping(value = "/enviocomunicacao/status/{id}", method = RequestMethod.GET)
    public ResponseEntity<StatusComunicacao> getStatusComunicacao(@PathVariable String id) {
        Optional<EnvioComunicacao> possibleEnvioComunicacao = getStatusAgendamentoPort.getAgendamento(id);
    	
        EnvioComunicacao comunicacao = possibleEnvioComunicacao.get();
    	return new ResponseEntity<StatusComunicacao>(new StatusComunicacao(comunicacao.getId(), comunicacao.getStatus()), HttpStatus.OK);
    }
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "/enviocomunicacao/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removerComunicacao(@PathVariable String id) {
        removerAgendamentoComunicaoPort.remover(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/enviocomunicacao", method = RequestMethod.POST)
    public ResponseEntity<ComunicacaoId> agendarComunicacao(
    		@Valid 
    		@RequestBody 
    		EnvioComunicacao envioComunicacao) {
        String idConsultaAgendamento = agendarSolicitacaoEnvioComunicacaoPort.agendar(envioComunicacao);
        if (idConsultaAgendamento != null) {
            return new ResponseEntity<ComunicacaoId>(new ComunicacaoId(idConsultaAgendamento), HttpStatus.CREATED);
        }

        return new ResponseEntity<ComunicacaoId>(HttpStatus.BAD_REQUEST);
    }

}
