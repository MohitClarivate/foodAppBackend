package com.training.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.foodApp.dto.Branch;

public interface SaveBranchRepository extends JpaRepository<Branch, Integer>{

}
