package com.revature.backend.users;

import lombok.Data;
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
		// TODO: hash password before passing on
		User user = new User(userDto);
		user.setCreatedAt(LocalDateTime.now());
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
//
//	public User updateUserById(Integer id, UserDto userDto) {
//		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
//		if (result.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//		}
//
//		return userRepository.save()
//	}
}
