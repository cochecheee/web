package com.example.project.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.entity.Customer;
import com.example.project.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ICustomerService customerService;
	
	@GetMapping("/{id}")
    public String home(@PathVariable("id") String id, ModelMap model) {
       
        Customer customer = customerService.findById(id).get();

        if (customer != null) {
            model.addAttribute("customer", customer);  
            return "views/customer/customer-home";  
        } else {
            return "redirect:/";
        }
    }
}
