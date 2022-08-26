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

import com.training.foodApp.dto.Menu;
import com.training.foodApp.service.MenuService;
import com.training.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {
	
	@Autowired
	MenuService service;
	
	//save menu
	@PostMapping("/savemenu")
	 public Menu savemenu(@RequestBody Menu menu) {
     return service.savemenu(menu);
	}
	
	//find all menu
	@GetMapping("/findallmenu")
	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu() {
		return service.findAllMenu();
	}
	
	//get menu by id
	@GetMapping("/getmenubyid/{id}")
	public ResponseEntity<ResponseStructure<Menu>> getMenuById(@PathVariable int id) {
		return service.getMenuById(id);
	}
	
	//delete menu by id
	@DeleteMapping("/deletemenu/{id}")
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(@PathVariable int id) {
		return service.deleteMenu(id);
	}

}
