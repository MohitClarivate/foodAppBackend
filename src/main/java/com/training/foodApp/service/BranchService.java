package com.training.foodApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.foodApp.dao.BranchDao;
import com.training.foodApp.dto.Branch;
import com.training.foodApp.exception.IdNotFoundException;
import com.training.foodApp.util.ResponseStructure;

@Service
public class BranchService {
	@Autowired
	BranchDao branchdao;
	
	//save branch
	public Branch saveBranch(Branch branch) {
        return branchdao.saveBranch(branch);
    }
	
	//find all branch
	public ResponseEntity<ResponseStructure<List<Branch>>> findAllBranch(){
		ResponseStructure<List<Branch>> structure=new ResponseStructure<List<Branch>>();
		structure.setMessage("All Branches Found sucessfully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setT(branchdao.findAllBranch());
		return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
	}
	
	//get branch by id 
	public ResponseEntity<ResponseStructure<Branch>> getBranchById(int id) {
		Optional<Branch> optional=branchdao.getBranchById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException();
		}else {
			ResponseStructure<Branch> structure=new ResponseStructure<Branch>();
			structure.setMessage("Branch Found sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(optional.get());
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}
	}
	
	//delete branch by id
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch(int id) {
		Branch branch=branchdao.deleteBranch(id);
		ResponseStructure<Branch> structure=new ResponseStructure<Branch>();
		if(branch!=null) {
			structure.setMessage("Branch deleted sucessfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setT(branch);
			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
//			structure.setMessage("invalid id");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setT(null);
//			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.NOT_FOUND);
		}
	}

}
