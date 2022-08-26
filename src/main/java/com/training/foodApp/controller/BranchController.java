package com.training.foodApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.training.foodApp.dto.Branch;
import com.training.foodApp.service.BranchService;
import com.training.foodApp.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BranchController {
	@Autowired
	BranchService service;
	
	//save branch
	@PostMapping("/savebranch")
	 public Branch saveBranch(@RequestBody Branch branch) {
      return service.saveBranch(branch);
	}
	
	//find all branch
	@GetMapping("/findallbranch")
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch() {
		return service.findAllBranch();
	}
	
	//get branch by id
	@GetMapping("/getbranchbyid/{id}")
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(@PathVariable int id) {
		return service.getBranchById(id);
	}
	
	//delete branch by id
	@DeleteMapping("/deletebranch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(@PathVariable int id) {
		return service.deleteBranch(id);
	}

}
