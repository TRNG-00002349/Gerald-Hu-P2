package com.revature.backend.likes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.revature.backend.posts.Post;
import com.revature.backend.users.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "likes", schema = "public",
		uniqueConstraints=
		@UniqueConstraint(columnNames={"user_id", "post_id"}))
public class Like {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	@JsonBackReference
	private Post post;

}
