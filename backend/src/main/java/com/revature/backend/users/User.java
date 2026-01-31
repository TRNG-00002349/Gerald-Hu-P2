package com.revature.backend.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.revature.backend.posts.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users", schema = "public")
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 4, message="username should be 4 characters or longer")
	@Pattern(regexp = "^\\S*$", message="whitespace in username is not allowed")
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Email
	@Column(name = "email", nullable = false, unique = true)
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
	@Column(name = "deleted", nullable = false)
	private Boolean deleted = false;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@OneToMany(mappedBy = "author")
	@JsonManagedReference
	private List<Post> userPosts;


	public User(UserDto userDto) {
		this.email = userDto.getEmail();
		this.hashedPassword = userDto.getPassword();
		this.username = userDto.getUsername();
	}
}