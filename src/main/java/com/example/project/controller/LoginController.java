package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Account;
import com.example.project.entity.Customer;
import com.example.project.entity.Manager;
import com.example.project.service.impl.CustomerServiceImpl;
import com.example.project.service.impl.ManagerServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	ManagerServiceImpl managerService;
	@Autowired
	CustomerServiceImpl customerService;

	@GetMapping("")
	public String login(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Không tạo mới session nếu chưa có
	    if (session != null && session.getAttribute("account") != null) {
	        // Nếu session đã tồn tại và người dùng đã đăng nhập, chuyển hướng đến trang phù hợp
	        String role = (String) session.getAttribute("role");
	        if ("manager".equals(role)) {
	            Account account = (Account) session.getAttribute("account");
	            Manager manager = managerService.findByUsername(account.getUsername());
	            return "redirect:/manager/" + manager.getIDManager();
	        } else if ("customer".equals(role)) {
	            Account account = (Account) session.getAttribute("account");
	            Customer customer = customerService.findByUserName(account.getUsername());
	            return "redirect:/customer/" + customer.getIDUser();
	        }
	        // shipper va admin
	    }
	    return "views/login";
	}

	@PostMapping("")
	public String processLogin(@RequestParam("username") String username, 
	                           @RequestParam("password") String password,
	                           HttpServletRequest request) {

	    // Tạo đối tượng Account để kiểm tra thông tin
	    Account acc = new Account();
	    acc.setUsername(username);
	    acc.setPassword(password);

	    // Tìm manager và customer từ username
	    Manager manager = managerService.findByUsername(username);
	    Customer customer = customerService.findByUserName(username);

	    // Khai báo session
	    HttpSession session = request.getSession(); 

	    // Kiểm tra tài khoản của Manager
	    if (manager != null && manager.getPassword().equals(password)) {
	        session.setAttribute("account", acc);
	        session.setAttribute("role", "manager");
	        return "redirect:/manager/" + manager.getIDManager();
	    } 
	    // Kiểm tra tài khoản của Customer
	    else if (customer != null && customer.getPassword().equals(password)) {
	        session.setAttribute("account", acc);
	        session.setAttribute("role", "customer");
	        return "redirect:/customer/" + customer.getIDUser();
	    } 
	    else {
	        // Xóa session nếu thông tin không hợp lệ
	        session.invalidate();
	        return "redirect:/login?error=true";
	    }
	}

}
