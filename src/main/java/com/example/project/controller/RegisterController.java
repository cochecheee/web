package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Account;
import com.example.project.entity.Customer;
import com.example.project.service.IAccountService;
import com.example.project.service.ICustomerService;
import com.example.project.service.impl.ManagerServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	ICustomerService customerService;
	@Autowired 
	IAccountService accountService;
	
	// chĩ có customer, mặc định role là customer
	@PostMapping("")
	public String register(@RequestParam("username") String username, 
	                       @RequestParam("password") String password,
	                       @RequestParam("fullname") String fullname,
	                       HttpServletRequest request) {
		
		//Kiểm tra trong Account toàn bộ có user name chưa
		Account account = accountService.findAccountByUserName(username);
	    

	    if (account != null) {
	        // Nếu customer đã tồn tại, chuyển hướng về trang đăng nhập với thông báo lỗi
	        return "redirect:/login?error=userExists";
	    }
	    
	    Account newAccount = new Account();
	    newAccount.setUsername(username);
	    newAccount.setPassword(password);
	    accountService.save(newAccount);
	    
	    // Tạo mới một customer
	    Customer newCustomer = new Customer();
	    newCustomer.setUsername(username);
	    newCustomer.setPassword(password);
	    newCustomer.setName(fullname);

	    // Lưu customer vào cơ sở dữ liệu
	    customerService.save(newCustomer);

	    // Tạo session và lưu thông tin customer
	    HttpSession session = request.getSession();
	    session.setAttribute("account", account);
	    session.setAttribute("role", "customer");

	    // Chuyển hướng đến trang chủ của customer sau khi đăng ký thành công
	    return "redirect:/login";
	}
}
