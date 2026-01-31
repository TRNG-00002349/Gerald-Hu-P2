package com.revature.backend.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	@Size(min = 4, message="username should be 4 characters or longer")
	private String username;

	@Email
	private String email;

	private String password;
}
