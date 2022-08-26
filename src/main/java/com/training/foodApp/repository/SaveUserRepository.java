package com.training.foodApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.foodApp.dto.User;

public interface SaveUserRepository extends JpaRepository<User, Integer>{
	
	public User findUserByEmail(String email);

}
