package com.revature.backend.likes;

import com.fasterxml.jackson.annotation.*;
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
	@JsonIgnore
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true) // https://stackoverflow.com/a/17583175
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	private Post post;

}
