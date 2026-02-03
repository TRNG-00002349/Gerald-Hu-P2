package com.revature.backend.users;

import com.revature.backend.auth.AuthDto;
import com.revature.backend.auth.InvalidCredentialsException;
import com.revature.backend.utils.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {

	private final UserRepository userRepository;

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
		// Polish: there has to be a better way to do this...
		if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
			user.setEmail(userDto.getEmail());
		}
		if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
			String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(8));
			user.setHashedPassword(hashedPassword);
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
		user.setDeleted(true);
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);
	}
}
