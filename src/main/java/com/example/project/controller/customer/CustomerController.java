package com.example.project.controller.customer;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.dto.CustomerRequestDTO;
import com.example.project.dto.OrderDTO;
import com.example.project.entity.Customer;
import com.example.project.entity.GoodsType;
import com.example.project.entity.PostOffice;
import com.example.project.entity.TransportType;
import com.example.project.service.IGoodsTypeService;
import com.example.project.service.impl.CustomerServiceImpl;
import com.example.project.service.impl.PostOfficeServiceImpl;
import com.example.project.service.impl.TransportTypeServiceImpl;
import com.example.project.utils.ConstantUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerServiceImpl customerService;
	@Autowired
	private PostOfficeServiceImpl postService;
	@Autowired
	private TransportTypeServiceImpl transportService;
	@Autowired
	IGoodsTypeService goodService;
	
	// OK, Chua test
	@GetMapping("/{id}")
    public String home(@PathVariable("id") String id, ModelMap model) {
       
        Customer customer = customerService.findById(id).get();
        
        if (customer != null) {
	    	String customerCity = customer.getCity();
	        List<PostOffice> posts = postService.findPostOfficeByCity(customerCity);
	        List<GoodsType> goodsType = goodService.findAll();
	        List<TransportType> transportType = transportService.findAll();
	        
	        model.addAttribute("goods",goodsType);
	        model.addAttribute("posts", posts);
	        model.addAttribute("customer", customer); 
	        model.addAttribute("transports", transportType);
	        model.addAttribute("orderDTO", new OrderDTO());
	        model.addAttribute("customerDTO", new CustomerRequestDTO());
            
	        return "views/customer/customer-home";  
        } else {
            return "redirect:/";
        }
    }
	
	// OK, Chua test
	@PostMapping("/edit/{id}")
	public String editCustomer(@PathVariable("id") String customerID, 
            							@ModelAttribute("customer") Customer customer,
            							HttpServletRequest req) {
		
		Customer oldCustomer = customerService.findById(customerID).get();
		String fileold = oldCustomer.getPicture();
		
		oldCustomer.setIDUser(customerID);
		oldCustomer.setAddress(customer.getAddress());
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setCccd(customer.getCccd());
		oldCustomer.setUsername(customer.getUsername());
		oldCustomer.setPassword(customer.getPassword());
		oldCustomer.setPhone(customer.getPhone());
		oldCustomer.setName(customer.getName());
		oldCustomer.setCity(customer.getCity());
		
		// Xử lý images
	    String fname = "";
	    String uploadPath = ConstantUtil.UPLOAD_PATH;
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdir();
	    }

	    try {
	        Part part = req.getPart("images1");
	        if (part.getSize() > 0) {
	            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	            String ext = filename.substring(filename.lastIndexOf(".") + 1);
	            fname = System.currentTimeMillis() + "." + ext;
	            part.write(uploadPath + "/" + fname);
	            oldCustomer.setPicture(fname);
	        } else {
	        	oldCustomer.setPicture(fileold);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    customerService.save(oldCustomer);
		
		return "redirect:/customer/" + customerID;
	}
	
	@PostMapping("/placeOrder")
	public String placeOrder(@ModelAttribute("orderDTO") OrderDTO orderDTO, 
														ModelMap model)
	{
		
		return "views/customer/paymentDetails";
	}
	
	@GetMapping("/cancleOrder")
	public String cancleOrder() {
		return "";
	}
	
	@GetMapping("")
	public String showPayment(ModelAttribute orderDTO) {
		return "views/customer/paymentDetails";
	}
	
	@PostMapping("")
	public String doPayment() {
		return "";
	}
}
