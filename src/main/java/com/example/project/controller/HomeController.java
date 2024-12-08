package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("") // Trang Home cua guest
	public String index() {
		return "views/index"; // Trả về tên file HTML (không cần phần mở rộng .html)
	}
}
