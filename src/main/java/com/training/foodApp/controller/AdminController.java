package com.training.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.training.foodApp.dto.User;
import com.training.foodApp.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	AdminService service;
	
	//find all branch manager
	@GetMapping("/findallBM")
	public List<User> getAllBM() {
	    return service.getAllBM();
	}

}
