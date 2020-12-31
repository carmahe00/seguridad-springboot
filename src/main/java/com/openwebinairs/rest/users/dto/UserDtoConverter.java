package com.openwebinairs.rest.users.dto;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.model.UserRole;

/**
 * clase para convertir de UserEntity to GetUserDto
 * @author juan
 *
 */
@Component
public class UserDtoConverter {

	public GetUserDto convertUserEntityToGetUserDto(UserEntity user) {
		return GetUserDto.builder()
				.username(user.getUsername())
				.avatar(user.getAvatar())
				.fullname(user.getFullname())
				.email(user.getEmail())
				.roles(user.getRoles().stream()
						.map(UserRole::name)
						.collect(Collectors.toSet()))
				.build();
	}
}
