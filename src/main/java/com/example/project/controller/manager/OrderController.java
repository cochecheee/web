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

import com.example.project.entity.Manager;
import com.example.project.entity.Order;
import com.example.project.entity.PostOffice;
import com.example.project.entity.Shipper;
import com.example.project.service.impl.ManagerServiceImpl;
import com.example.project.service.impl.OrderServiceImpl;
import com.example.project.service.impl.PostOfficeServiceImpl;

@Controller
@RequestMapping("/manager/")
public class OrderController {
	@Autowired 
	private OrderServiceImpl orderService;
	@Autowired
	private PostOfficeServiceImpl postService;
	@Autowired
	private ManagerServiceImpl managerService;
	
	@GetMapping("order/edit/{id}")
    public String editOrder(@PathVariable("id") String id, 
    											ModelMap model, 
    											@RequestParam("managerID") String managerID) {
        Order order = orderService.findById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("managerID", managerID);
        
        Manager manager = managerService.findById(managerID).get();
        List<Shipper> shippers = manager.getShippers();	
        
        // post office
        List<PostOffice> posts = postService.findPostOfficeByCity("Hanoi");
        if (posts == null) {
        	System.out.println("Wrong");
			
		}
        model.addAttribute("posts", posts);
        model.addAttribute("shippers", shippers);
        return "views/manager/order-edit";
    }
	
	@PostMapping("order/edit")
	public String editOrderView(@ModelAttribute("order") Order order, 
								@RequestParam("postOffice") String idPost, 
								@RequestParam("shipper") String idShipper,
								@RequestParam("managerID") String managerID) {
		// System.out.println(idPost);
		PostOffice post = postService.findById(idPost).get();
		order.setPostOffice(post);
		
		// Shipper shipper = shipperService.findByID(idShipper).get();
		// order.setShipper(shipper);
		
	    orderService.save(order); // Lưu đối tượng order vào database
	    return "redirect:/manager/" + managerID; // Điều hướng sau khi lưu
	}
	
	@GetMapping("orders")
	public String showOrders(ModelMap model, @RequestParam("managerID") String managerID) {
		
		Manager manager = managerService.findById(managerID).get();
		String postOfficeId = manager.getPost().getIDPost();
		
		List<Order> orders = orderService.filterOrderByPostID(postOfficeId);
		if(orders != null) {
			System.out.println("khác null");
		}
		
		model.addAttribute("orders", orders);
		model.addAttribute("managerID", managerID);
		
		return "views/manager/order-details";
	}
}
