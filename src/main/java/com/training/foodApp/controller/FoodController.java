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
import org.springframework.web.bind.annotation.RestController;

import com.training.foodApp.dto.Food;
import com.training.foodApp.service.FoodService;
import com.training.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodController {
	
	@Autowired
	FoodService service;
	
	//add food
	@PostMapping("/addfood")
	 public Food addFood(@RequestBody Food food) {
    return service.savefood(food);
	}
	
	//find all food
	@GetMapping("/findallfood")
	public ResponseEntity<ResponseStructure<List<Food>>> findAllFood() {
		return service.findAllFood();
	}
	
	//get food by id
	@GetMapping("/getfoodbyid/{id}")
	public ResponseEntity<ResponseStructure<Food>> getFoodById(@PathVariable int id) {
		return service.getFoodById(id);
	}
	
	//delete food by id
	@DeleteMapping("/deletefood/{id}")
	public ResponseEntity<ResponseStructure<Food>> deleteFood(@PathVariable int id) {
		return service.deleteFood(id);
	}

}
