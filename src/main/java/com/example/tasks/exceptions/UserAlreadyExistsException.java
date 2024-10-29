package com.example.tasks.exceptions;

public class UserAlreadyExistsException extends Exception{

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
