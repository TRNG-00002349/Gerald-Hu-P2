package com.revature.backend.comments;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {

	private Integer authorId;

	@NotBlank
	private String content;

	private Integer postId;
}
