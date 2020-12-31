package com.openwebinairs.rest.users.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetPedidoDto {

	private String fullName;
	private String avatar;
	private String email;
	private LocalDateTime fecha;
	private Float total;
}
