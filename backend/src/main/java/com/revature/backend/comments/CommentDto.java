package com.revature.backend.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {

	private Integer authorId;

	@NotBlank
	private String content;

	@NotNull
	private Integer postId;
}
