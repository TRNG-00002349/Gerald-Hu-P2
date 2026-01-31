package com.revature.backend.posts;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
public class PostController {

	private final PostService postService;

	@PostMapping("/api/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public Post postBlogPost(@Valid @RequestBody PostDto postDto) {
		return postService.createBlogPost(postDto);
	}

	@GetMapping("/api/posts/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public Post getBlogPostById(@PathVariable Integer postId) {
		return postService.readBlogPostById(postId);
	}

	@GetMapping("/api/users/{userId}/posts")
	@ResponseStatus(HttpStatus.OK)
	public List<Post> getUserBlogPosts(@PathVariable Integer userId) {
		return postService.readUserBlogPosts(userId);
	}

	@PutMapping("/api/posts/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public Post putBlogPost(@PathVariable Integer postId, @Valid @RequestBody PostDto postDto) {
		return postService.updateBlogPost(postId, postDto);
	}

	@DeleteMapping("/api/posts/{postId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBlogPost(@PathVariable Integer postId) {
		postService.deleteBlogPost(postId);
	}
}
