package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("") // Trang Home cua guest
	public String index() {
		return "views/index"; // Trả về tên file HTML (không cần phần mở rộng .html)
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Lấy session hiện tại, không tạo mới
	    if (session != null) {
	        session.invalidate(); // Xóa toàn bộ dữ liệu trong session
	    }
	    return "redirect:/login"; // Chuyển hướng người dùng về trang đăng nhập
	}
}
