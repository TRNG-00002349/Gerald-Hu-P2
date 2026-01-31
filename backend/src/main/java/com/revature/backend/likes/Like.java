package com.revature.backend.likes;

import com.revature.backend.posts.Post;
import com.revature.backend.users.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "likes", schema = "public")
public class Like {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

}
