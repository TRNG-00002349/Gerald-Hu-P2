package com.revature.backend.posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostDto {

	@NotBlank
	private String content;
}
