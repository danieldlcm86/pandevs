package com.pandevs.pandevs.exceptions;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// Método constructor va a recibir un parámetro para poder evaluar y lanzar la Excepción
	public UserNotFoundException(Long id) {
		super("El usuario con el Id: " + id + " no existe." );
	}

}
