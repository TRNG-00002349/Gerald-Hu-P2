package com.revature.backend.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Entity
@Valid
@Table(name = "users", schema = "public")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 4, message="username should be 4 characters or longer")
	@Column(name = "username", nullable = false)
	private String username;

	@JsonIgnore
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@JsonIgnore
	@Column(name = "hashed_password", nullable = false)
	private String hashedPassword;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@Column(name = "deleted")
	private Boolean deleted;

//	@JsonInclude(JsonInclude.Include.NON_NULL)
//	private List<Post> userPosts;
}