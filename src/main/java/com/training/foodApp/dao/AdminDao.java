package com.training.foodApp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.foodApp.dto.User;
import com.training.foodApp.repository.AdminRepository;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminRepository adminrepository;
	
	//find all BM
	public List<User> getAllBM() {
		return adminrepository.findAll();
	}

}
