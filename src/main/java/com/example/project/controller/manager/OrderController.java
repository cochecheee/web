package com.example.project.controller.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Order;
import com.example.project.entity.PostOffice;
import com.example.project.service.impl.OrderServiceImpl;
import com.example.project.service.impl.PostOfficeServiceImpl;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/manager/order")
public class OrderController {
	@Autowired 
	OrderServiceImpl orderService;
	@Autowired
	PostOfficeServiceImpl postService;
	
	@GetMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") String id, ModelMap model, @RequestParam("managerID") String managerID) {
        Order order = orderService.findById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("managerID", managerID);
        
        // post office
        List<PostOffice> posts = postService.findPostOfficeByCity("Hanoi");
        if (posts == null) {
        	System.out.println("Wrong");
			
		}
        model.addAttribute("posts", posts);
        return "views/manager/order-edit";
    }
	@PostMapping("/edit")
	public String createOrder(@ModelAttribute("order") Order order, @RequestParam("postOffice") String idPost, @RequestParam("managerID") String managerID) {
		System.out.println(idPost);
		PostOffice post = postService.findById(idPost).get();
		order.setPostOffice(post);
		
	    orderService.save(order); // Lưu đối tượng order vào database
	    return "redirect:/manager/" + managerID; // Điều hướng sau khi lưu
	}
}
