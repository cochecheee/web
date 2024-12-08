package com.example.project.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.project.entity.Customer;
import com.example.project.entity.Manager;
import com.example.project.entity.Shipper;
import com.example.project.service.impl.ManagerServiceImpl;

@Controller
public class ManagerController {
	@Autowired
	ManagerServiceImpl managerService;
	
	@GetMapping("/manager/{id}")
	public String managerHome(@PathVariable("id") String id, ModelMap model) {
	    // Tìm Manager theo ID
	    Manager manager = managerService.findById(id).get();
	    
	    // Lấy danh sách Customer
	    List<Customer> customers = manager.getUsers();
	    
	    // Truyền thông tin vào model
	    model.addAttribute("manager", manager);
	    
	    // Truền post office
	    model.addAttribute("customers", customers);
	    
	    List<Shipper> shippers = manager.getShippers(); 
	    model.addAttribute("shippers", shippers);
	    
	    
	    return "views/manager/manager-home";
	}
	
	@GetMapping("/manager/addPost")
	public String addPost() {
		return "views/manager/addPost";
	}
	

}
