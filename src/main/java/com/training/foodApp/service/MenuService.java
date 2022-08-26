package com.training.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.training.foodApp.dao.MenuDao;
import com.training.foodApp.dto.Menu;
import com.training.foodApp.exception.IdNotFoundException;
import com.training.foodApp.util.ResponseStructure;

@Service
public class MenuService {
	
	@Autowired
	MenuDao menudao;
	
	//add menu
	public Menu savemenu(Menu menu) {
		return menudao.savemenu(menu);
	}
	
	//find all menu
	public ResponseEntity<ResponseStructure<List<Menu>>> findAllMenu() {
		ResponseStructure<List<Menu>> structure=new ResponseStructure<List<Menu>>();
		structure.setMessage("All Menu's Found sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(menudao.findAllMenu());
		return new ResponseEntity<ResponseStructure<List<Menu>>>(structure, HttpStatus.OK);
	}
	
	//get menu by id 
	public ResponseEntity<ResponseStructure<Menu>> getMenuById(int id) {
		Optional<Menu> optional=menudao.getMenuById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException();
		}else {
			ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
			structure.setMessage("Menu Found sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		}
	}
	
	//delete menu by id
	public ResponseEntity<ResponseStructure<Menu>> deleteMenu(int id) {
		Menu menu=menudao.deleteMenu(id);
		ResponseStructure<Menu> structure=new ResponseStructure<Menu>();
		if(menu!=null) {
			structure.setMessage("Menu deleted sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(menu);
			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
//			structure.setMessage("invalid id");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setT(null);
//			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	

}
