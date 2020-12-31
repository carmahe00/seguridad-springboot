package com.openwebinairs.rest.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinairs.rest.users.dto.CreateUserDto;
import com.openwebinairs.rest.users.dto.GetUserDto;
import com.openwebinairs.rest.users.dto.UserDtoConverter;
import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.services.UserEntityService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserEntityService userEntityService;

	@Autowired
	private UserDtoConverter userDtoConverter;

	/**
	 * 
	 * @param newUser usuario enviado desde cliente
	 * @return ResponseEntity 200
	 */
	@PostMapping("/")
	public ResponseEntity<GetUserDto> nuevoUsuario(@RequestBody CreateUserDto newUser) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userDtoConverter.convertUserEntityToGetUserDto(userEntityService.nuevoUsuario(newUser)));
	}

	/**
	 * @param user es de tipo UserEntity se puede obtener la información de usuario
	 * @AuthenticationPrincipal obtiene del contexto de seguridad(token
	 *                          authentication información de usuario)
	 * @return
	 */
	@GetMapping("/me")
	public GetUserDto yo(@AuthenticationPrincipal UserEntity user) {
		return userDtoConverter.convertUserEntityToGetUserDto(user);
	}
}
