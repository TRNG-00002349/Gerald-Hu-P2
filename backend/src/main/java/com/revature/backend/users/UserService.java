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

//	public User createUser(NewUserDto newUserDto) {
//
//		// TODO: hash password before passing on
//		User user = new User(newUserDto);
//
//		return userRepository.save(user);
//	}

	public List<User> getAllUsers() {
		return userRepository.findAllByDeletedFalse();
	}

	public User getUserById(Integer id) {
		Optional<User> result = userRepository.findByIdAndDeletedFalse(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
