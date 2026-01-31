package com.revature.backend.posts;

import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.EntityNotFoundException;
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

	public Post createBlogPost(@Valid PostDto postDto) {
		// TODO: authorId shouldn't be part of the postDto, it should be obtained from user token/cookie.
		Integer authorId = postDto.getAuthorId();
		Optional<User> author = userRepository.findByIdAndDeletedFalse(authorId);
		if (author.isEmpty()) {
			throw new EntityNotFoundException("user", authorId);
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
		// TODO: AUTH: pull userId from token, etc
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		} // else if author not found, or author is the wrong author, throw an exception

		Post p = post.get();
		p.setContent(postDto.getContent());
		p.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(p);
	}

	public void deleteBlogPost(Integer postId) {
		// TODO: auth, make sure user is [author of post] OR [admin role]
		Optional<Post> post = postRepository.findById(postId);
		if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		postRepository.deleteById(postId);
	}
}
