package com.revature.backend.posts;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostDto {

	private Integer authorId;

	@NotBlank
	private String content;
}
