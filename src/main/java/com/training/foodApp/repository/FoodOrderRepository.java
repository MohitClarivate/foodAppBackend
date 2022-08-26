package com.training.foodApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.foodApp.dto.FoodOrders;

public interface FoodOrderRepository extends JpaRepository<FoodOrders, Integer>{
	public Optional<FoodOrders> findFoodOrderByPhone(long phone);


}
