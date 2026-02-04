package com.revature.backend.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CommentDto {

	@NotBlank
	private String content;

	private Integer postId;
}
