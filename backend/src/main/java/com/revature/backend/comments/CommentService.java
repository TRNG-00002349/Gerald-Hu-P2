package com.revature.backend.comments;

import com.revature.backend.auth.InvalidCredentialsException;
import com.revature.backend.posts.Post;
import com.revature.backend.posts.PostRepository;
import com.revature.backend.users.User;
import com.revature.backend.users.UserRepository;
import com.revature.backend.utils.EntityNotFoundException;
import com.revature.backend.utils.IAuthenticationFacade;
import jakarta.validation.Valid;
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
	private final IAuthenticationFacade authenticationFacade;

	private void checkCommentOwnership(Comment comment) {
		/*
		 * Check if the logged-in user can modify the comment specified by the URL path.
		 * TODO: add role-based check; if logged-in as admin, should be able to bypass ownership check.
		 */
		Integer claimedUserId = authenticationFacade.getClaimedUserId();

		Optional<User> claimedUser = userRepository.findByIdAndDeletedFalse(claimedUserId);
		if (claimedUser.isEmpty()) {
			throw new InvalidCredentialsException(
					String.format(
							"Current user not found",
							claimedUserId));
		}

		if (!claimedUserId.equals(comment.getAuthor().getId())) {
			throw new InvalidCredentialsException(claimedUserId, "comment", comment.getId());
		}
	}

	public Comment createComment(@Valid CommentDto commentDto) {
		Integer authorId = authenticationFacade.getClaimedUserId();
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

	public Comment updateComment(Integer commentId, @Valid CommentDto commentDto) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new EntityNotFoundException("comment", commentId);
		}

		Comment c = comment.get();
		checkCommentOwnership(c);

		c.setUpdatedAt(LocalDateTime.now());
		c.setContent(commentDto.getContent());
		return commentRepository.save(c);
	}

	public void deleteComment(Integer commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isEmpty()) {
			throw new EntityNotFoundException("comment", commentId);
		}

		Comment c = comment.get();
		checkCommentOwnership(c);

		commentRepository.deleteById(commentId);
	}
}
