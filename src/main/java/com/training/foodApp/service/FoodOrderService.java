package com.training.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.training.foodApp.dao.FoodDao;
import com.training.foodApp.dao.FoodOrdersDao;
import com.training.foodApp.dto.Food;
import com.training.foodApp.dto.FoodOrders;
import com.training.foodApp.exception.IdNotFoundException;
import com.training.foodApp.util.ResponseStructure;

@Service
public class FoodOrderService {
	@Autowired
	FoodOrdersDao foodordersdao;
	
	@Autowired
	FoodDao fooddao;
	
	@Autowired
	JavaMailSender javaMailSender;

	
	//save food order
	public FoodOrders foodOrder(FoodOrders foodorder) {
		List<Food> foodlist=foodorder.getFoods();
		int price=0;
		for(Food i : foodlist) {
			Optional<Food> temp = fooddao.getFoodById(i.getId());
			price += temp.get().getPrice();
		}
		foodorder.setPrice(price);
		foodorder.setQuantity(foodlist.size());
		return foodordersdao.foodOrder(foodorder);
	}
	
	//show all orders
	public List<FoodOrders> getAllOrder() {
		return foodordersdao.findAllOrder();
	}
	
	//show order by id
	public ResponseEntity<ResponseStructure<FoodOrders>> getFoodOrderById(int id) {
		Optional<FoodOrders> optional=foodordersdao.getFoodOrderById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException();
		}else {
			ResponseStructure<FoodOrders> structure=new ResponseStructure<FoodOrders>();
			structure.setMessage("Food Order Found sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<FoodOrders>>(structure, HttpStatus.OK);
		}
	}
	
	//update order by id
	public ResponseEntity<ResponseStructure<FoodOrders>> updateFoodOrderById(FoodOrders foodOrder, int id) {
        List<Food> foods = foodOrder.getFoods();
        int price = 0;
        for (Food i : foods) {
            Optional<Food> food = fooddao.getFoodById(i.getId());
                price += food.get().getPrice();
        }
        foodOrder.setQuantity(foods.size());
        foodOrder.setPrice(price);
        FoodOrders temp = foodordersdao.updateFoodOrderById(foodOrder, id);
        ResponseStructure<FoodOrders> structure = new ResponseStructure<FoodOrders>();
        if (temp != null) {
            structure.setMessage("FoodOrder Updated Successfully!");
            structure.setStatus(HttpStatus.OK.value());
            structure.setT(temp);
            return new ResponseEntity<ResponseStructure<FoodOrders>>(structure, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException();
        }
    }
	
	//get bill by id
	public ResponseEntity<ResponseStructure<String>> getbillbyid(int id, String email) {
        Optional<FoodOrders> foodOrder = foodordersdao.getFoodOrderById(id);
        int gross_amount = foodOrder.get().getPrice();
        double gst = gross_amount * 0.12;
        double service_charge = gross_amount * 0.05;
        double net_amount = gross_amount + gst + service_charge;
        
        StringBuilder stringBuilder = new StringBuilder();
        int temp = 1;
        for(Food i:foodOrder.get().getFoods()) {
            stringBuilder.append(temp +  "\t\t" + i.getName() + "\t\t" + i.getPrice() + "\n");
            temp++;
        }
        
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@foodapp.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Bill for your order from Food-App is here");
        simpleMailMessage.setText("Order Id: " + id + "\n" +
                //"Date: " + foodOrder.get().getDate() + "\n" +
                "Branch: " + foodOrder.get().getBranch().getName() + ", " + foodOrder.get().getBranch().getCity() + ", " + foodOrder.get().getBranch().getPhone() + "\n" +
                "Name: " + foodOrder.get().getName() + "\n\n" +
                "Sr no." + "\t" + "Food Name" + "\t" + "Price\n" +
                "----------------------------------------\n" +
                stringBuilder +
                "----------------------------------------\n\n" +
                "Gross Amount: " + gross_amount + "\n" +
                "GST @12%: " + gst + "\n" +
                "Service Charge @5%: " + service_charge + "\n" +
                "Net Payable Amount: (" + gross_amount + "+" + gst + "+" + service_charge + ") = " + net_amount + "\n\n\n\n" +
                "Thank you!\n"+"Team Food App"
                );

       javaMailSender.send(simpleMailMessage);
        
//     String message = simpleMailMessage.getText();
        
       ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage("FoodOrder found!");
        structure.setStatus(HttpStatus.OK.value());
        structure.setT("Bill generated and sent to email!");
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
}
	
	//get bill by phone
	public ResponseEntity<ResponseStructure<String>> getbillbyphone(int phone, String email){
		Optional<FoodOrders> foodOrder = foodordersdao.findFoodOrderByPhone(phone);
		int id = foodOrder.get().getId();
		int gross_amount = foodOrder.get().getPrice();
        double gst = gross_amount * 0.12;
        double service_charge = gross_amount * 0.05;
        double net_amount = gross_amount + gst + service_charge;
        
        StringBuilder stringBuilder = new StringBuilder();
        int temp = 1;
        for(Food i:foodOrder.get().getFoods()) {
            stringBuilder.append(temp +  "\t\t" + i.getName() + "\t\t" + i.getPrice() + "\n");
            temp++;
        }
        
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@foodapp.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Bill for your order from Food-App is here");
        simpleMailMessage.setText("Order Id: " + id + "\n" +
                //"Date: " + foodOrder.get().getDate() + "\n" +
                "Branch: " + foodOrder.get().getBranch().getName() + ", " + foodOrder.get().getBranch().getCity() + ", " + foodOrder.get().getBranch().getPhone() + "\n" +
                "Name: " + foodOrder.get().getName() + "\n\n" +
                "Sr no." + "\t" + "Food Name" + "\t" + "Price\n" +
                "----------------------------------------\n" +
                stringBuilder +
                "----------------------------------------\n\n" +
                "Gross Amount: " + gross_amount + "\n" +
                "GST @12%: " + gst + "\n" +
                "Service Charge @5%: " + service_charge + "\n" +
                "Net Payable Amount: (" + gross_amount + "+" + gst + "+" + service_charge + ") = " + net_amount + "\n\n\n\n" +
                "Thank you!\n"+"Team Food App"
                );

       javaMailSender.send(simpleMailMessage);
        
//     String message = simpleMailMessage.getText();
        
       ResponseStructure<String> structure = new ResponseStructure<String>();
        structure.setMessage("FoodOrder found!");
        structure.setStatus(HttpStatus.OK.value());
        structure.setT("Bill generated and sent to email!");
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}
	
	
	//delete order by id
	public ResponseEntity<ResponseStructure<FoodOrders>> deleteOrder(int id) {
		FoodOrders food=foodordersdao.deleteOrder(id);
		ResponseStructure<FoodOrders> structure=new ResponseStructure<FoodOrders>();
		if(food!=null) {
			structure.setMessage("order deleted sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(food);
			return new ResponseEntity<ResponseStructure<FoodOrders>>(structure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
//			structure.setMessage("invalid id");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setT(food);
//			return new ResponseEntity<ResponseStructure<FoodOrders>>(structure, HttpStatus.NOT_FOUND);
		}
	}

}
