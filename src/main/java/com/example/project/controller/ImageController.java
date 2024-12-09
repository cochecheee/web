package com.example.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.utils.ConstantUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@GetMapping("/{fname}")
    public void loadImage(@PathVariable("fname") String fname, HttpServletResponse response) throws IOException, ServletException {
		String fileName = fname;
		File file = new File(ConstantUtil.UPLOAD_PATH + "\\" + fileName);
		response.setContentType("image/jpeg");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
    }
}
