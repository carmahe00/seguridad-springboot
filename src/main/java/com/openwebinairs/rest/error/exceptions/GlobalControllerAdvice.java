package com.openwebinairs.rest.error.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author juan
 * @RestControllerAdvice manejo de excepciones globales
 */
@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(NewUserWithDifferentPasswordsException.class)
	public ResponseEntity<ApiError> handleNewUserErrors(Exception ex){
		return buildErrorReponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	@ExceptionHandler(PedidoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNotFoundErrors(Exception ex){
		return buildErrorReponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		//ApiError apiError = new ApiError(status, ex.getMessage());
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	/**
	 * 
	 * @param status estado de respuesta
	 * @param mensaje mensaje de NewUserWithDifferentPasswordsException
	 * @return 
	 */
	private ResponseEntity<ApiError> buildErrorReponseEntity(HttpStatus status, String mensaje){
		return ResponseEntity.status(status)
				.body(ApiError.builder()
						.estado(status)
						.mensaje(mensaje)
						.build());
	}
}
