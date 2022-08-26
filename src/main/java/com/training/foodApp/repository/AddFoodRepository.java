package com.training.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.foodApp.dto.Food;

public interface AddFoodRepository extends JpaRepository<Food, Integer>{

}
