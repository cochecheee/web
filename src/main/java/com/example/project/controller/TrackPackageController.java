package com.example.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Order;
import com.example.project.service.IOrderService;

@Controller
public class TrackPackageController {
	
	@Autowired
	private IOrderService orderService;
	
	public TrackPackageController(IOrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping("/trackOrder")
	public String trackOrder(@RequestParam("orderCode") String orderCode, ModelMap model) {
		
		Optional<Order> optionalOrder = orderService.findById(orderCode);

	    if (optionalOrder.isPresent()) {
	        model.addAttribute("order", optionalOrder.get());
	    } else {
	        model.addAttribute("error", "Order not found for the given code: " + orderCode);
	    }

	    return "views/trackOrder";
	}
}
