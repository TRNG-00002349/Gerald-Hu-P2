package com.revature.backend.users;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {

	private final UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAllByDeletedFalse();
	}

}
