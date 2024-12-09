package com.example.project.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.project.entity.Customer;
import com.example.project.entity.Manager;
import com.example.project.entity.Order;
import com.example.project.entity.Shipper;
import com.example.project.service.impl.ManagerServiceImpl;
import com.example.project.service.impl.OrderServiceImpl;

@Controller
public class ManagerController {
	@Autowired
	ManagerServiceImpl managerService;
	@Autowired
	OrderServiceImpl orderService;
	
	@GetMapping("/manager/{id}")
	public String managerHome(@PathVariable("id") String id, ModelMap model) {
	    // Tìm Manager theo ID
	    Manager manager = managerService.findById(id).get();
	    
	    // Lấy danh sách Customer
	    List<Customer> customers = manager.getUsers();
	    // Truyền thông tin vào model
	    model.addAttribute("manager", manager);
	    model.addAttribute("customers", customers);
	    model.addAttribute("managerID",id);
	    
	    //id post
	    String postid = manager.getPost().getIDPost();
	    System.out.println(postid);
//	    
	    List<Order> orders = orderService.filterOrderByPostID(postid);
	    //System.out.println(orders);
	    model.addAttribute("orders", orders);
	    
	    
	    List<Shipper> shippers = manager.getShippers(); 
	    model.addAttribute("shippers", shippers);
	    
	    return "views/manager/manager-home";
	}
	
	@GetMapping("/manager/customer/edit/{id}")
	public String editCustomer(@PathVariable("id") String customerID) {
		
	}
	

}
