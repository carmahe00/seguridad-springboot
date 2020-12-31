package com.openwebinairs.rest.security.jwt.model;

import java.util.Set;

import com.openwebinairs.rest.users.dto.GetUserDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * clase para agregar en la respuesta el token
 * 
 * @author juan
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDto {

	private String token;

	@Builder(builderMethodName =  "jwtUserResponseBuilder")
	public JwtUserResponse(String username, String avatar, String fullname, String email, Set<String> roles,
			String token) {
		super(username, avatar, fullname, email, roles);
		this.token = token;
	}

}
