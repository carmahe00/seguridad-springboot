package com.openwebinairs.rest.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

	private String username;
	private String avatar;
	private String password;
	private String password2;
	private String fullname;
	private String email;
}
