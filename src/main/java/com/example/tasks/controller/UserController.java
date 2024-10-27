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
import com.example.tasks.repository.UserRepo;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping
	public ResponseEntity<String> registration(@RequestBody UserEntity user) {
		try {
			userRepo.save(user);
			return ResponseEntity.ok("User succesfully saved.");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Error.");
		}
	}
	
	@GetMapping
	public ResponseEntity<String> getUsers() {
		try {

			String s = StreamSupport.stream(userRepo.findAll().spliterator(), false)
		            .map(UserEntity::getUsername)
		            .collect(Collectors.joining(", "));
			return ResponseEntity.ok(s);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Error.");
		}
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String getUserss() {
		try {
			return "Second Ok";
		}
		catch (Exception e) {
			return "Error.";
		}
	}
	
}