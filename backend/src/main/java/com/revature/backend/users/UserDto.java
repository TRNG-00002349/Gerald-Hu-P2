package com.revature.backend.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

	@NotBlank
	private String username;

	@Email
	private String email;

	@NotBlank
	private String password;
}
