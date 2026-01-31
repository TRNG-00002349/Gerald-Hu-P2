package com.revature.backend.posts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.backend.comments.Comment;

import com.revature.backend.likes.Like;
import com.revature.backend.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posts", schema = "public")
public class Post {

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	@Column(name = "content", nullable = false)
	private String content;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private LocalDateTime updatedAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(mappedBy = "post")
	@JsonManagedReference
	private List<Comment> commentList;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(mappedBy = "post")
	private List<Like> likesList;

}
