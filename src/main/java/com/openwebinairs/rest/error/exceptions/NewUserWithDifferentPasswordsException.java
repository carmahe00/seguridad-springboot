package com.openwebinairs.rest.error.exceptions;

/**
 * clase excepción para la contraseña no coincide
 * 
 * @author juan
 *
 */
public class NewUserWithDifferentPasswordsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NewUserWithDifferentPasswordsException() {
		super("La contraseña no coincide");
	}

}
