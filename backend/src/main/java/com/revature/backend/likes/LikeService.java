package com.revature.backend.likes;

import com.revature.backend.posts.Post;
import com.revature.backend.posts.PostRepository;
import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class LikeService {

	private final LikeRepository likeRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public Like createLike(Integer postId) {
		// TODO: authorId from cookie, etc. for now it's hardcoded to 1. yikesTM
		// ALso there's probably a better way of checking that the post exists, instead of
		// writing it out every single time. but i am TIRED.
		Optional<User> user = userRepository.findByIdAndDeletedFalse(1);
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Like like = new Like();
		like.setPost(post.get());
		like.setUser(user.get());
		return likeRepository.save(like);
	}
}
