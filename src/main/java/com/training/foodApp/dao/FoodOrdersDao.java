package com.training.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.training.foodApp.dto.FoodOrders;
import com.training.foodApp.repository.FoodOrderRepository;

@Repository
public class FoodOrdersDao {
	@Autowired
	private FoodOrderRepository foodorderrepository;

	//save food order
	public FoodOrders foodOrder(FoodOrders foodorder) {
		return foodorderrepository.save(foodorder);
	}
	
	//get all food order
	public List<FoodOrders> findAllOrder() {
		return foodorderrepository.findAll();
	}

	//get food order by id 
	public Optional<FoodOrders> getFoodOrderById(int id) {
		return foodorderrepository.findById(id);
	}
	
	//update order by id
	public FoodOrders updateFoodOrderById(FoodOrders foodOrder, int id) {
		if (foodorderrepository.findById(id).isEmpty()) {
			return null;
		} else {
	        //FoodOrders order = getFoodOrderById(id).get();
	        foodOrder.setId(id);
            return foodorderrepository.save(foodOrder);
		}
	}
	
	//get bill by phone
	public Optional<FoodOrders> findFoodOrderByPhone(int phone) {
		return foodorderrepository.findFoodOrderByPhone(phone);
	}
	
	//get bill by id 
	public Optional<FoodOrders> getBillById(int id) {
		return foodorderrepository.findById(id);
	}

	//delete food order by id 
	public FoodOrders deleteOrder(int id) {
		if (foodorderrepository.findById(id).isEmpty()) {
			return null;
		} else {
	        FoodOrders order = getFoodOrderById(id).get();
	        foodorderrepository.delete(order);
	        return order;
		}
	}

}
