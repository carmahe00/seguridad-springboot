package com.openwebinairs.rest.error.exceptions;

/**
 * clase para manejar excepción cuando el usuario no tiene pedido
 * @author juan
 *
 */
public class PedidoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PedidoNotFoundException() {
		super("Pedido no encontrado");
	}
	
	
}
