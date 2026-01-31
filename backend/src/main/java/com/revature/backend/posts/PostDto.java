package com.revature.backend.posts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDto {

	@NotNull
	private Integer authorId;

	@NotBlank
	private String content;
}
