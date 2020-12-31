package com.openwebinairs.rest.error.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * clase para mostrar error
 * 
 * @author juan
 *
 */
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

	@NotNull
	private HttpStatus estado;
	
	/**
	 * @Builder.Default indica valor por defecto
	 * @JsonFormat formato de fecha
	 */
	@Builder.Default
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime fecha = LocalDateTime.now();
	@NonNull
	private String mensaje;
	public ApiError(HttpStatus estado, @NonNull String mensaje) {
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	
}
