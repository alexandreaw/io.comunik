package io.comunik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.comunik.models.ErrorMessage;
import io.comunik.service.ComunicacaoNotFoundException;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleError(Exception ex) {
		return new ResponseEntity<ErrorMessage>(
                ErrorMessage.builder().message(ex.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
	}
	
	@ExceptionHandler(ComunicacaoNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleError(ComunicacaoNotFoundException ex) {
		return new ResponseEntity<ErrorMessage>(
                ErrorMessage.builder().message(ex.getMessage()).build(),
                HttpStatus.NOT_FOUND
        );
	}
}