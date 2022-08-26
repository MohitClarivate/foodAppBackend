package com.training.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.foodApp.dto.User;

public interface AdminRepository extends JpaRepository<User, Integer>{

}
