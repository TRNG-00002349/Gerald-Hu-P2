package com.revature.backend.users;

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
		String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(16));
		user.setHashedPassword(hashedPassword);
		return userRepository.save(user);
	}

	public List<User> readAllUsers() {
		return userRepository.findAllByDeletedFalse();
	}

	public User readUserById(Integer id) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return result.get();
	}

	public User updateUserById(Integer id, UserDto userDto) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		User user = result.get();
		// Polish: there has to be a better way to do this...
		if (userDto.getEmail() != null && !userDto.getEmail().isBlank()) {
			user.setEmail(userDto.getEmail());
		}
		if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
			user.setHashedPassword(userDto.getPassword()); // TODO: hash
		}
		user.setUpdatedAt(LocalDateTime.now());
		return userRepository.save(user);
	}

	public void deleteUserById(Integer id) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		User user = result.get();
		user.setDeleted(true);
		user.setUpdatedAt(LocalDateTime.now());
		userRepository.save(user);
	}
}
