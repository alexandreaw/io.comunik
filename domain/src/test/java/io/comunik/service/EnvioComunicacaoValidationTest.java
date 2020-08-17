package io.comunik.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.comunik.models.EnvioComunicacao;
import io.comunik.models.TipoComunicacao;

public class EnvioComunicacaoValidationTest {
	
	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
    public void deveRetornatMensagemDeErroParaDataEnvio() {
		var envioComunicacao = EnvioComunicacao.builder()
				.destinatario("alexandreaw@gmail.com")
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
        
        Set<ConstraintViolation<EnvioComunicacao>> violations = validator.validate(envioComunicacao);
        assertTrue(violations.size() == 1);
        
        var row = violations.stream().findFirst().get();
        assertEquals("Data envio não pode ser vazio", row.getMessage());
    }
	
	@Test
    public void deveRetornatMensagemDeErroParaDestinatario() {
		var envioComunicacao = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.mensagem("Mensagem")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
        
        Set<ConstraintViolation<EnvioComunicacao>> violations = validator.validate(envioComunicacao);
        assertTrue(violations.size() == 1);
        
        var row = violations.stream().findFirst().get();
        assertEquals("Destinatario não pode ser vazio", row.getMessage());
    }
	
	@Test
    public void deveRetornatMensagemDeErroParaMensagem() {
		var envioComunicacao = EnvioComunicacao.builder()
				.dataHoraEnvio(LocalDateTime.now().plusHours(1))
				.destinatario("alexandreaw@gmail.com")
				.tipoComunicacao(TipoComunicacao.EMAIL)
				.build();
        
        Set<ConstraintViolation<EnvioComunicacao>> violations = validator.validate(envioComunicacao);
        assertTrue(violations.size() == 1);
        
        var row = violations.stream().findFirst().get();
        assertEquals("Mensagem não pode ser vazio", row.getMessage());
    }

}
