package com.example.tasks.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.tasks.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long>{
	UserRepo findByUsername(String username);
}
