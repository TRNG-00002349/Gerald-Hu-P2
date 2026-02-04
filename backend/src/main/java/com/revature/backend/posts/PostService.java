package com.revature.backend.posts;

import com.revature.backend.auth.InvalidCredentialsException;
import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.AuthenticationFacade;
import com.revature.backend.utils.EntityNotFoundException;
import com.revature.backend.utils.IAuthenticationFacade;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final IAuthenticationFacade authenticationFacade;

	private void checkPostOwnership(Post post) {
		/*
		 * Check if the logged-in user can modify the blog post specified by the URL path.
		 * TODO: add role-based check; if logged-in as admin, should be able to bypass ownership check.
		 */
		Integer claimedUserId = authenticationFacade.getClaimedUserId();

		Optional<User> claimedUser = userRepository.findByIdAndDeletedFalse(claimedUserId);
		if (claimedUser.isEmpty()) {
			throw new InvalidCredentialsException(
					String.format(
							"Current user %s not found",
							claimedUserId));
		}

		if (!claimedUserId.equals(post.getAuthor().getId())) {
			throw new InvalidCredentialsException(claimedUserId, "post", post.getId());
		}
	}

	public Post createBlogPost(@Valid PostDto postDto) {
		Integer claimedUserId = authenticationFacade.getClaimedUserId();
		Optional<User> author = userRepository.findByIdAndDeletedFalse(claimedUserId);
		if (author.isEmpty()) {
			throw new EntityNotFoundException("user", claimedUserId);
		}

		Post post = new Post();
		post.setContent(postDto.getContent());
		post.setAuthor(author.get());
		post.setCreatedAt(LocalDateTime.now());

		return postRepository.save(post);
	}

	public List<Post> readUserBlogPosts(Integer userId) {
		Optional<User> author = userRepository.findByIdAndDeletedFalse(userId);
		if (author.isEmpty()) {
			throw new EntityNotFoundException("user", userId);
		}
		return postRepository.findByAuthorId(userId);
	}

	public Post readBlogPostById(Integer postId) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}
		return post.get();
	}

	public Post updateBlogPost(Integer postId, @Valid PostDto postDto) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Post p = post.get();
		checkPostOwnership(p);

		p.setContent(postDto.getContent());
		p.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(p);
	}

	public void deleteBlogPost(Integer postId) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Post p = post.get();
		checkPostOwnership(p);

		postRepository.deleteById(postId);
	}
}
