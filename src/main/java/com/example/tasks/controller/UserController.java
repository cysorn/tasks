package com.example.tasks.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tasks.entity.UserEntity;
import com.example.tasks.exceptions.UserAlreadyExistsException;
import com.example.tasks.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<String> registration(@RequestBody UserEntity user) {
		try {
			userService.registration(user);
			return ResponseEntity.ok("User succesfully saved.");
		}
		catch (UserAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<String> getUsers() {
		try {
			return ResponseEntity.ok("Currently registered users: " + userService.getRegisteredUsernames());
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Error.");
		}
	}
}