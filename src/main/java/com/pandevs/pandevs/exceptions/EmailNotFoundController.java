package com.pandevs.pandevs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// Para indicar que esta clase es un Controlador de Excepciones agrego la anotación @ControllerAdvice
@ControllerAdvice
public class EmailNotFoundController {

	// La clase recibe anotaciones que nos permiten controlar el método de la Excepción y aplicarlo en métodos de tipo ResponseEntity
	// Método para manejar la excepción y que retorna un mensaje (.getMessage());
	@ResponseBody
	@ExceptionHandler(EmailNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFoundHandler(EmailNotFoundException e) {
		return e.getMessage();
	}
	
}
