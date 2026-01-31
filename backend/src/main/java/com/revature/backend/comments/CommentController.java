package com.revature.backend.comments;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@Data
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comment postComment(@Valid @RequestBody CommentDto commentDto) {
		return commentService.createComment(commentDto);
	}

	@GetMapping("/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public Comment getCommentById(@PathVariable Integer commentId) {
		return commentService.readCommentById(commentId);
	}

	@PutMapping("/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public Comment putCommentById(@PathVariable Integer commentId, @Valid @RequestBody CommentDto commentDto) {
		return commentService.updateComment(commentId, commentDto);
	}

	@DeleteMapping("/{commentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCommentById(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
	}


}
