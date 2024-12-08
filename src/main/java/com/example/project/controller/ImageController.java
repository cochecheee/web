package com.example.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageController {
	@GetMapping("")
	public void loadImage(HttpServletRequest req, HttpServletResponse resp, @RequestParam("fname") String fname) {
		String fileName = req.getParameter("fname");
		File file = new File("D:/lt-web/Week04/upload" + "/" + fileName);
		resp.setContentType("image/jpeg");
		if (file.exists()) {
			try {
				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
