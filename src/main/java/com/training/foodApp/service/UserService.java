package com.training.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.foodApp.dao.BranchDao;
import com.training.foodApp.dao.UserDao;
import com.training.foodApp.dto.Food;
import com.training.foodApp.dto.FoodOrders;
import com.training.foodApp.dto.User;
import com.training.foodApp.exception.AlreadyExist;
import com.training.foodApp.exception.UserNotFound;
import com.training.foodApp.util.AES;
import com.training.foodApp.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao userdao;
	
	@Autowired
	AES aes;
	
	@Autowired
	BranchDao branchdao;
	
	//add user
	public User saveUser(User user) {
		User user2 = userdao.findUserByEmail(user.getEmail());
		if(user2!=null) {
			throw new AlreadyExist();
		}else if(branchdao.getBranchById(user.getBranch().getId()).isEmpty())
		{
			throw new UserNotFound();
		}
		else {
			user.setPassword(aes.encrypt(user.getPassword(),"encryptkey"));
	        return userdao.saveUser(user);
		}

    }
	
	//update user by id
	public ResponseEntity<ResponseStructure<User>> updateUserById(User user, int id) {
		User temp = userdao.getUserById(id).get();
		temp.setName(user.getName());
		temp.setRole(user.getRole());
		temp.setBranch(user.getBranch());
		temp.setEmail(user.getEmail());
		temp.setPassword(user.getPassword());
		temp.setPassword(aes.encrypt(user.getPassword(),"encryptkey"));
	       User temp2 = userdao.updateUserById(temp, id);
	        ResponseStructure<User> structure = new ResponseStructure<User>();
	        if (temp2 != null) {
	            structure.setMessage("User Updated Successfully!");
	            structure.setStatus(HttpStatus.OK.value());
	            structure.setT(temp2);
	            return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	        } else {
	        	throw new UserNotFound();
	        }
	}
	
	//find all user
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
		ResponseStructure<List<User>> structure=new ResponseStructure<List<User>>();
		structure.setMessage("All Users Found sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(userdao.findAllUser());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}
//	
//	//find user by email
//	public ResponseEntity<ResponseStructure<User>> findUserByEmail(String email) {
//		User user = userdao.findUserByEmail(email);
//		if(user.isEmpty()) {
//			throw new IdNotFoundException();
//		}else {
//			ResponseStructure<User> structure=new ResponseStructure<User>();
//			structure.setMessage("User Found sucessfully");
//			structure.setStatus(HttpStatus.OK.value());
//			structure.setT(user.get());
//			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
//		}
//	}
	
	//check email exist
	public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
		User user = userdao.findUserByEmail(email);
		if(user==null) {
			throw new UserNotFound();
		}
		else if(!user.getPassword().contains(aes.encrypt(password, "encryptkey"))){
			throw new UserNotFound();
		}else {
			ResponseStructure<User> structure=new ResponseStructure<User>();
			structure.setMessage("User Login sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(user);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

	}
	
	//get user by id 
	public ResponseEntity<ResponseStructure<User>> getUserById(int id) {
		Optional<User> optional=userdao.getUserById(id);
		
		User temp=userdao.getUserById(id).get();
		temp.setPassword(aes.decrypt(temp.getPassword(),"encryptkey"));
		
		if(optional.isEmpty()) {
			throw new UserNotFound();
		}else {
			ResponseStructure<User> structure=new ResponseStructure<User>();
			structure.setMessage("User Found sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
	}
	
	//delete user by id
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id) {
		User user=userdao.deleteUser(id);
		ResponseStructure<User> structure=new ResponseStructure<User>();
		if(user!=null) {
			structure.setMessage("User deleted sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(user);
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}else {
			throw new UserNotFound();
//			structure.setMessage("invalid id");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setT(null);
//			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
		}
	}


}
