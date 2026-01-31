package com.revature.backend.comments;

import com.revature.backend.posts.Post;
import com.revature.backend.posts.PostRepository;
import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Data
public class CommentService {

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	public Comment createComment(CommentDto commentDto) {
		// TODO: auth, author id, etc
		Integer authorId = commentDto.getAuthorId();
		Integer postId = commentDto.getPostId();
		Optional<User> author = userRepository.findByIdAndDeletedFalse(authorId);
		Optional<Post> post = postRepository.findById(postId);
		if (author.isEmpty()) {
			throw new EntityNotFoundException("user", authorId);
		} else if (post.isEmpty()) {
			throw new EntityNotFoundException("post", postId);
		}

		Comment comment = new Comment();
		comment.setAuthor(author.get());
		comment.setPost(post.get());
		comment.setContent(commentDto.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		return commentRepository.save(comment);
	}

	public Comment readCommentById(Integer commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new EntityNotFoundException("comment", commentId);
		}
		return comment.get();
	}
}
