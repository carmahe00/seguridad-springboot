package com.openwebinairs.rest.security.jwt;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openwebinairs.rest.security.jwt.model.JwtUserResponse;
import com.openwebinairs.rest.security.jwt.model.LoginRequest;
import com.openwebinairs.rest.users.dto.GetUserDto;
import com.openwebinairs.rest.users.dto.UserDtoConverter;
import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.model.UserRole;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	/**
	 * @param authenticationManager sirve para autenticar al usuario
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider tokenProvider;
	@Autowired
	private UserDtoConverter converter;
	
	@PostMapping("/auth/login")
	public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest){
		//Autenticar al usuario
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		//Guarda en el contexto de seguridad
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserEntity user = (UserEntity) authentication.getPrincipal();
		String jwtToken = tokenProvider.generateToken(authentication);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(convertUserEntityAndTokenToJwtUserResponse(user, jwtToken));
	}

	public GetUserDto me(@AuthenticationPrincipal UserEntity user) {
		return converter.convertUserEntityToGetUserDto(user);
	}
	
	/**
	 * 
	 * @param user usuario
	 * @param jwtToken token
	 * @return convierte con user y jwtToken un JwtUserResponse
	 */
	private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(UserEntity user, String jwtToken) {
		return JwtUserResponse
				.jwtUserResponseBuilder()
				.fullname(user.getFullname())
				.email(user.getEmail())
				.username(user.getUsername())
				.avatar(user.getAvatar())
				.roles(user.getRoles().stream().map(UserRole::name).collect(Collectors.toSet()))
				.token(jwtToken)
				.build();
	}
}
