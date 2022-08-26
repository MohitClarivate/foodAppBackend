package com.training.foodApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.training.foodApp.dto.Menu;

public interface SaveMenuRepository extends JpaRepository<Menu, Integer>{

}
