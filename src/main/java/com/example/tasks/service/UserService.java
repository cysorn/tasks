package com.example.tasks.service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.tasks.entity.UserEntity;
import com.example.tasks.exceptions.UserAlreadyExistsException;
import com.example.tasks.repository.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
		if(userRepo.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistsException("This username is already registered.");
		}
		return userRepo.save(user);
	}
	
	public String getRegisteredUsernames() {
		return StreamSupport.stream(userRepo.findAll().spliterator(), false)
        .map(UserEntity::getUsername)
        .collect(Collectors.joining(", "));
	}
}
