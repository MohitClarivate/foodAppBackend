package com.training.foodApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.training.foodApp.dto.FoodOrders;
import com.training.foodApp.service.FoodOrderService;
import com.training.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodOrderController {
	
	@Autowired
	FoodOrderService service;
	
	//order food
	@PostMapping("/foodorder")
	public FoodOrders foodOrder(@RequestBody FoodOrders foodorder) {
      return service.foodOrder(foodorder);
    }
	
	//find all Order
	@GetMapping("/findallorder")
	public List<FoodOrders> getAllOrder() {
		return service.getAllOrder();
	}
	
	//get order by id
	@GetMapping("/getorderbyid/{id}")
	public ResponseEntity<ResponseStructure<FoodOrders>> getFoodOrderById(@PathVariable int id) {
		return service.getFoodOrderById(id);
	}
	
	//update order by id
	@PutMapping("/updateorder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrders>> updateFoodOrderById(@RequestBody FoodOrders foodorder, @PathVariable int id) {
		return service.updateFoodOrderById(foodorder , id);
	}
	
	//get Bill by id
	@GetMapping("/showbillbyemail")
	public ResponseEntity<ResponseStructure<String>> getbill(@RequestParam int id, @RequestParam String email){
		return service.getbillbyid(id,email);
	}
	
	//get Bill by phone
	@GetMapping("/showbillbyphone")
	public ResponseEntity<ResponseStructure<String>> getbillphone(@RequestParam int phone, @RequestParam String email){
		return service.getbillbyphone(phone,email);
	}
	
	//delete order by id
	@DeleteMapping("/deleteorder/{id}")
	public ResponseEntity<ResponseStructure<FoodOrders>> deleteOder(@PathVariable int id) {
		return service.deleteOrder(id);
	}

	
}
