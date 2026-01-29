package com.revature.backend.likes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Valid
@Table(name = "likes", schema = "public")
public class Like {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "user_id")
	@NotNull
	private Integer userId;

	@Column(name = "post_id")
	@NotNull
	private Integer postId;

}
