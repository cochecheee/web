package com.example.project.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.entity.Order;
import com.example.project.service.impl.OrderServiceImpl;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/manager/order")
public class OrderController {
	@Autowired 
	OrderServiceImpl orderService;
	
	@GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") String id, ModelMap model) {
        Order order = orderService.findById(id).get();
        model.addAttribute("order", order);
        return "views/manager/order-edit";
    }
}
