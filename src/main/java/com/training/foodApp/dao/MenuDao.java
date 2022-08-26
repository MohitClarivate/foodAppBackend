package com.training.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.training.foodApp.dto.Menu;
import com.training.foodApp.repository.SaveMenuRepository;

@Repository
public class MenuDao {
	@Autowired
	private SaveMenuRepository savemenurepository;
	
	//save menu
	public Menu savemenu(Menu menu) {
		return savemenurepository.save(menu);
	}
	
	//find all menu
	public List<Menu> findAllMenu() {
		return savemenurepository.findAll();
	}
	
	//find menu by id
	public Optional<Menu> getMenuById(int id) {
		return savemenurepository.findById(id);
	}
	
	//delete menu by id
	public Menu deleteMenu(int id) {
		if (savemenurepository.findById(id).isEmpty()) {
			return null;
		} else {
			Menu menu = savemenurepository.findById(id).get();
			savemenurepository.delete(menu);
	        return menu;
		}
	}

}
