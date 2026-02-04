package com.revature.backend.likes;

import com.revature.backend.posts.Post;
import com.revature.backend.posts.PostRepository;
import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.EntityNotFoundException;
import com.revature.backend.utils.IAuthenticationFacade;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
public class LikeService {

	private final LikeRepository likeRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final IAuthenticationFacade authenticationFacade;

	public Like createLike(Integer postId) {
		Integer claimedUserId = authenticationFacade.getClaimedUserId();

		Optional<User> user = userRepository.findByIdAndDeletedFalse(claimedUserId);
		Optional<Post> post = postRepository.findById(postId);
		if (user.isEmpty()) {
			throw new EntityNotFoundException("user", claimedUserId);
		}
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Like like = new Like();
		like.setPost(post.get());
		like.setUser(user.get());
		return likeRepository.save(like);
	}

	public void deleteLike(Integer postId) {
		Integer claimedUserId = authenticationFacade.getClaimedUserId();

		Optional<User> user = userRepository.findByIdAndDeletedFalse(claimedUserId);
		Optional<Post> post = postRepository.findById(postId);
		if (user.isEmpty()) {
			throw new EntityNotFoundException("user", claimedUserId);
		}
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Optional<Like> optionalLike = likeRepository.findByUserIdAndPostId(claimedUserId, postId);
		if (optionalLike.isEmpty()) {
			throw new EntityNotFoundException("like");
		}

		Like like = optionalLike.get();
		likeRepository.delete(like);
	}
}
