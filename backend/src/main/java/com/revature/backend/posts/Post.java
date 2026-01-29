package com.revature.backend.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.revature.backend.comments.Comment;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Valid
@Table(name = "posts", schema = "public")
public class Post {

	@Id
	@GeneratedValue
	private Integer id;
	private String content;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer authorId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private LocalDateTime createdAt;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private LocalDateTime updatedAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany
	private List<Comment> commentList;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Integer> likesList;

}
