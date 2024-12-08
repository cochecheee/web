package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.entity.Manager;
import com.example.project.service.impl.ManagerServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	ManagerServiceImpl managerService;
	
	@GetMapping("")
	public String login() {
		return "views/login";
	}
	
	@PostMapping("")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {
		// Tìm tài khoản dựa trên username (hoặc email nếu cần)
		Manager manager = managerService.findByUsername(username);
		
		System.out.println(username + " " + password); // In ra để kiểm tra

		if (manager != null && manager.getPassword().equals(password)) {
			System.out.println("ok");
			return "redirect:/manager/" + manager.getIDManager();
		} else {
			return "redirect:/login"; // Nếu thất bại, quay lại trang đăng nhập
		}
	}
	
}
