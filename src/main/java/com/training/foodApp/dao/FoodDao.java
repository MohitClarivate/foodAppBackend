package com.training.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.foodApp.dto.Food;
import com.training.foodApp.repository.AddFoodRepository;

@Repository
public class FoodDao {
	@Autowired
	private AddFoodRepository addfoodrepository;

	//add food
	public Food savefood(Food food) {
		return addfoodrepository.save(food);
	}
	
	//find all food
	public List<Food> findAllFood(){
		return addfoodrepository.findAll();
	}
	
	//find food by id
	public Optional<Food> getFoodById(int id) {
		return addfoodrepository.findById(id);
	}
	
	//delete food by id
	public Food deleteFood(int id) {
		if (addfoodrepository.findById(id).isEmpty()) {
			return null;
		} else {
	        Food food = getFoodById(id).get();
	        addfoodrepository.delete(food);
	        return food;
		}
	}
	
}
