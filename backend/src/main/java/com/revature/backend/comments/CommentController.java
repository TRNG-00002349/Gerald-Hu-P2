package com.revature.backend.comments;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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


	public ResponseEntity<Comment> putComment() {
		return ResponseEntity.ok(new Comment());
	}

	public ResponseEntity<Comment> deleteComment() {
		return ResponseEntity.ok(new Comment());
	}


}
