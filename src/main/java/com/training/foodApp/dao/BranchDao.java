package com.training.foodApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.training.foodApp.dto.Branch;
import com.training.foodApp.repository.SaveBranchRepository;

@Repository
public class BranchDao {
	@Autowired
	private SaveBranchRepository savebranchrepository;
	
	//save branch
	public Branch saveBranch(Branch branch) {
		return savebranchrepository.save(branch);
	}
	
	//find all branch
	public List<Branch> findAllBranch(){
		return savebranchrepository.findAll();
	}

	//find branch by id
	public Optional<Branch> getBranchById(int id) {
		return savebranchrepository.findById(id);
	}
	
	//delete branch by id
	public Branch deleteBranch(int id) {
		if (savebranchrepository.findById(id).isEmpty()) {
			return null;
		} else {
	        Branch branch = getBranchById(id).get();
	        savebranchrepository.delete(branch);
	        return branch;
		}
	}

}
