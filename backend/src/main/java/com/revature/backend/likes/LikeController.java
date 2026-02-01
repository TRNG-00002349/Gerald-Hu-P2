package com.revature.backend.likes;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/likes")
@Data
public class LikeController {

	private final LikeService likeService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Like postLike(@PathVariable Integer postId) {
		return likeService.createLike(postId);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLike(@PathVariable Integer postId) {
		likeService.deleteLike(postId);
	}
}
