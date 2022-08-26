package com.training.foodApp.service;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.foodApp.dao.AdminDao;
import com.training.foodApp.dto.User;

@Service
public class AdminService {
	
	@Autowired
	AdminDao admindao;
	
	//get all branch manager
	public List<User> getAllBM() {
        List<User> branchManagers = admindao.getAllBM();
        ListIterator<User> listIterator = branchManagers.listIterator();
        while (listIterator.hasNext()) {
            if (!listIterator.next().getRole().contains("bm")) {
                listIterator.remove();
            }
        }
        return branchManagers;
    }

}
