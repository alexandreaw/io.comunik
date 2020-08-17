package io.comunik.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.comunik.models.ComunicacaoId;
import io.comunik.models.EnvioComunicacao;
import io.comunik.ports.AgendarSolicitacaoEnvioComunicacaoPort;

@RestController
public class EnvioComunicacaoRestController {
	
	private AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort;
	
	@Autowired
	public EnvioComunicacaoRestController(AgendarSolicitacaoEnvioComunicacaoPort agendarSolicitacaoEnvioComunicacaoPort) {
		super();
		this.agendarSolicitacaoEnvioComunicacaoPort = agendarSolicitacaoEnvioComunicacaoPort;
	}

	@RequestMapping(value = "/enviocomunicacao", method = RequestMethod.POST)
    public ResponseEntity<ComunicacaoId> agendarComunicacao(@Valid @RequestBody EnvioComunicacao envioComunicacao) {
        String idConsultaAgendamento = agendarSolicitacaoEnvioComunicacaoPort.agendar(envioComunicacao);
        if (idConsultaAgendamento != null) {
            return new ResponseEntity<ComunicacaoId>(new ComunicacaoId(idConsultaAgendamento), HttpStatus.CREATED);
        }

        return new ResponseEntity<ComunicacaoId>(HttpStatus.BAD_REQUEST);
    }

}
