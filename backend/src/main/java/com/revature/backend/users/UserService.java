package com.revature.backend.users;

import com.revature.backend.auth.AuthDto;
import com.revature.backend.auth.InvalidCredentialsException;
import com.revature.backend.utils.EntityNotFoundException;
import com.revature.backend.utils.IAuthenticationFacade;
import jakarta.validation.Valid;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {

	private final UserRepository userRepository;
	private final IAuthenticationFacade authenticationFacade;

	private void checkUserOwnership(User user) {
		/*
		 * Check if the logged-in user can modify the user specified by the URL path.
		 * TODO: add role-based check; if logged-in as admin, should be able to bypass ownership check.
		 */
		/* POLISH: There's probably a better way to check user ownership, e.g. using filters or intercepts.
		However, considering "user owns entity" is business logic, perhaps it *should* live in a service.
		 */

		Integer claimedUserId = authenticationFacade.getClaimedUserId();

		Optional<User> claimedUser = userRepository.findByIdAndDeletedFalse(claimedUserId);
		if (claimedUser.isEmpty()) {
			throw new InvalidCredentialsException(
					String.format(
							"Current user %s not found",
							claimedUserId));
		}

		if (!claimedUserId.equals(user.getId())) {
			throw new InvalidCredentialsException(claimedUserId, "user", user.getId());
		}
	}

	public User createUser(UserDto userDto) {
		User user = new User(userDto);
		user.setDeleted(false);
		user.setCreatedAt(LocalDateTime.now());
		String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(8));
		user.setHashedPassword(hashedPassword);
		return userRepository.save(user);
	}

	public User login(@Valid AuthDto authDto) {
		String username = authDto.getUsername();
		Optional<User> optionalUser = userRepository.findByUsernameAndDeletedFalse(username);
		if (optionalUser.isEmpty()) {
			throw new EntityNotFoundException("user", username);
		}
		User user = optionalUser.get();
		if (BCrypt.checkpw(authDto.getPassword(), user.getHashedPassword())) {
			return user;
		} else {
			throw new InvalidCredentialsException("invalid username or password");
		}
	}

	public List<User> readAllUsers() {
		return userRepository.findAllByDeletedFalse();
	}

	public User readUserById(Integer id) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("user", id);
		}
		return result.get();
	}

	public User updateUserById(Integer id, UserDto userDto) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("user", id);
		}

		User user = result.get();
		checkUserOwnership(user);

		// Polish: there has to be a better way to do this...
		if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
			user.setEmail(userDto.getEmail());
		}
		if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
			String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(8));
			user.setHashedPassword(hashedPassword);
		}
		if (userDto.getUsername() != null && !userDto.getUsername().isBlank()) {
			user.setUsername(userDto.getUsername());
		}
		user.setUpdatedAt(LocalDateTime.now());
		return userRepository.save(user);
	}

	public void deleteUserById(Integer id) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("user", id);
		}

		User user = result.get();
		checkUserOwnership(user);

		user.setDeleted(true);
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);
	}
}
