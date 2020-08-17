package io.comunik.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.comunik.models.ErrorMessage;
import io.comunik.service.ComunicacaoNotFoundException;
import io.comunik.service.DataHoraInvalidException;
import lombok.Generated;

@ControllerAdvice
@Generated
public class ExceptionHandlingController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleError(Exception ex) {
		return new ResponseEntity<ErrorMessage>(ErrorMessage.builder().message(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ComunicacaoNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleError(ComunicacaoNotFoundException ex) {
		return new ResponseEntity<ErrorMessage>(ErrorMessage.builder().message(ex.getMessage()).build(),
				HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({DataHoraInvalidException.class})
	public ResponseEntity<ErrorMessage> handleError(DataHoraInvalidException ex) {
		return new ResponseEntity<ErrorMessage>(ErrorMessage.builder().message(ex.getMessage()).build(),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		var builder = new StringBuilder();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			builder.append(error.getDefaultMessage());
			builder.append(", ");
		});
		
		return new ResponseEntity<ErrorMessage>(ErrorMessage.builder().message(builder.toString()).build(),
				HttpStatus.BAD_REQUEST);
	}
}