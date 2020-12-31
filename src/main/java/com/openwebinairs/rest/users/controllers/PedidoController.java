package com.openwebinairs.rest.users.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;

import com.openwebinairs.rest.error.exceptions.PedidoNotFoundException;
import com.openwebinairs.rest.users.model.Pedido;
import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.model.UserRole;
import com.openwebinairs.rest.users.services.PedidoServicio;


@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoServicio pedidoServicio;
	
	@GetMapping("/")
	public ResponseEntity<?> pedidos(Pageable pageable, HttpServletRequest request, @AuthenticationPrincipal UserEntity user){
		Page<Pedido> result = null;
		if(user.getRoles().contains(UserRole.ADMIN)) {
			result = pedidoServicio.findAll(pageable);
		}else {
			result = pedidoServicio.findAllByUser(user, pageable);
		}
		
		if(result.isEmpty()) {
			throw new PedidoNotFoundException();
		} else {
			//Genenera enlaces
			//UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURI().toString());
			
		}
		return null;
	}
}
