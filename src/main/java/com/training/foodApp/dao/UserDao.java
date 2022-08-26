package com.training.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.training.foodApp.dto.User;
import com.training.foodApp.repository.SaveUserRepository;

@Repository
public class UserDao {
	@Autowired
	private SaveUserRepository saveuserrepository;
	
	//save user
	public User saveUser(User user) {
		return saveuserrepository.save(user);	
	}
	
	//find all user
	public List<User> findAllUser() {
		return saveuserrepository.findAll();
	}
	
	//find user by email
	public User findUserByEmail(String email) {
		return saveuserrepository.findUserByEmail(email);
	}
	
	//find user by id 
	public Optional<User> getUserById(int id) {
		return saveuserrepository.findById(id);
	}
	
	//delete user by id
	public User deleteUser(int id) {
		if (saveuserrepository.findById(id).isEmpty()) {
			return null;
		} else {
	        User user = getUserById(id).get();
	        saveuserrepository.delete(user);
	        return user;
		}
	}

}
