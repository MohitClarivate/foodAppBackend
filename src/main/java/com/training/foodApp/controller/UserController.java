package com.training.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.foodApp.dto.User;
import com.training.foodApp.service.UserService;
import com.training.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService service;
	
	//save user
	@PostMapping("/saveuser")
	public User saveUser(@RequestBody User user) {
       return service.saveUser(user);
    }
	
	//find all user
	@GetMapping("/findalluser")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
		return service.findAllUser();
	}
	
//	//find user by email
//	@GetMapping("/finduserbyemail")
//	public ResponseEntity<ResponseStructure<User>> findUserByEmail(@RequestParam String email) {
//		return service.findUserByEmail(email);
//	}
	
	//login check by mail id
	@GetMapping("/loginUser")
	public ResponseEntity<ResponseStructure<User>> loginUser(@RequestParam String email, @RequestParam String password) {
		return service.loginUser(email,password);
	}
	
	//get user by id
	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int id) {
		return service.getUserById(id);
	}
		
	//delete user by id
	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable int id) {
		return service.deleteUser(id);
	}

}
