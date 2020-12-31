package com.openwebinairs.rest.users.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.openwebinairs.rest.error.exceptions.NewUserWithDifferentPasswordsException;
import com.openwebinairs.rest.services.base.BaseService;
import com.openwebinairs.rest.users.dto.CreateUserDto;
import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.model.UserRole;
import com.openwebinairs.rest.users.repos.UserEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserEntityService extends BaseService<UserEntity, Long, UserEntityRepository> {

	private final PasswordEncoder passwordEncoder;

	Optional<UserEntity> findByUsername(String username) {
		return this.repositorio.findByUsername(username);
	}

	/**
	 * método para guardar el usuario. primero: encripyta contraseña. segundo:
	 * asigna Set rol(java 8). DataIntegrityViolationException si hay violación a la
	 * base de datos
	 * 
	 * @param userEntity enviado desde cliente
	 * @return
	 */
	public UserEntity nuevoUsuario(CreateUserDto newUser) {
		/*
		 * userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		 * userEntity.setRoles(Stream.of(UserRole.USER).collect(Collectors.toSet())); //
		 * Set.of(UserRole.USER); java 9 return save(userEntity);
		 */
		if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
			UserEntity userEntity = UserEntity.builder().username(newUser.getUsername())
					.password(passwordEncoder.encode(newUser.getPassword())).avatar(newUser.getAvatar())
					.email(newUser.getEmail()).fullname(newUser.getFullname())
					.roles(Stream.of(UserRole.USER).collect(Collectors.toSet())).build();
			try {
				return save(userEntity);
			} catch (DataIntegrityViolationException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nombre de usuario ya existe");
			}
		} else {
			throw new NewUserWithDifferentPasswordsException();
		}
	}
}
