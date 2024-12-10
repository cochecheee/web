package com.example.project.controller.shipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.example.project.entity.Order;
import com.example.project.entity.Shipper;
import com.example.project.model.ShipperModel;
import com.example.project.service.impl.OrderServiceImpl;
import com.example.project.service.impl.ShipperServiceImpl;
import com.example.project.utils.ConstantUtil;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ShipperController {
	@Autowired
	ShipperServiceImpl shipperService;

	@Autowired
	OrderServiceImpl orderService;

	@GetMapping("/shipper/{id}")
	public String shipperHome(@PathVariable("id") String id, Model model) {
		Shipper shipper = shipperService.findByID(id).get();

		// Lấy danh sách các đơn hàng chưa giao
		List<Order> orderShipping = orderService.findByIdShipperAndStatus(id, 3);

		// Lấy danh sách các đơn hàng giao thất bại
		List<Order> orderFailed = orderService.findByIdShipperAndStatus(id, 5);

		// Lấy danh sách các đơn hàng giao thành công
		List<Order> orderDelivered = orderService.findByIdShipperAndStatus(id, 4);

		String idShipper = id;

		// Truyền thông tin vào model
		model.addAttribute("ordersShipping", orderShipping);
		model.addAttribute("ordersFailed", orderFailed);
		model.addAttribute("ordersDelivered", orderDelivered);
		model.addAttribute("id", idShipper);
		return "views/shipper/shipper-home";
	}

	@GetMapping("/shipper/profile/{id}")
	public String Profile(@PathVariable("id") String id, Model model) {
		Shipper shipper = shipperService.findByID(id).get();

		// Số sao của Shipper
		Integer star = shipperService.StarRateShipper(id);

		model.addAttribute("star", star != null ? star : 0);
		model.addAttribute("shipper", shipper);

		return "views/shipper/shipper-profile";
	}

	// Controller để xử lý yêu cầu chỉnh sửa profile
	@GetMapping("shipper/profile/edit/{id}")
	public String editProfile(@PathVariable("id") String idShipper, Model model) {
		// Lấy thông tin shipper từ database
		Shipper shipper = shipperService.findByID(idShipper).get();
		model.addAttribute("shipper", shipper);
		return "views/shipper/editProfile"; // Trả về trang HTML có form chỉnh sửa
	}

	@PostMapping("shipper/profile/update/{id}")
	public String updateProfile(@PathVariable("id") String IDShipper, @RequestParam("name") String name,
			@RequestParam("phone") String phone, @RequestParam("address") String address,
			@RequestParam("city") String city, @RequestParam("statusShipper") Boolean statusShipper,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			@RequestParam("gender") Boolean gender, @RequestParam("cccd") String cccd,
			@RequestParam("birth") Date birth,
			HttpServletRequest req, Model model) {

		Shipper oldShipper = shipperService.findByID(IDShipper).get();
		Boolean isPassword = false;
		
		//Gán Shipper nhũng thông tin cập nhật
		Shipper shipper = new Shipper();
		shipper.setIDShipper(IDShipper);
		shipper.setName(name);
		shipper.setPhone(phone);
		shipper.setAddress(address);
		shipper.setCity(city);
		shipper.setStatusShipper(statusShipper);
		shipper.setGender(gender);
		shipper.setCccd(cccd);
		shipper.setBirth(birth);
		
		
		// Hình cũ
	    String fileOld = oldShipper.getPicture();

	    // Link từ request
	    String images = req.getParameter("images");

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
	            shipper.setPicture(fname);
	        } else if (images != null) {
	            shipper.setPicture(images);
	        } else {
	            shipper.setPicture(fileOld);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		

		// Kiểm tra mật khẩu oldPassword, cần đảm bảo người dùng nhập đúng password để
		// cập nhật thông tin
		if (oldPassword.equals(oldShipper.getPassword())) {
			isPassword = true; // Nếu mật khẩu nhập vào đúng thì isPassword = true
		}

		// Nếu oldPassword, kiểm tra ô newPassword có rỗng không
		if (newPassword != null && !newPassword.isEmpty()) {
			shipper.setPassword(newPassword); // Nếu không rỗng gán password mới cho shipper
		} else {
			shipper.setPassword(oldShipper.getPassword()); 
		}

		// Nếu oldPassword, không rỗng và newPassword: isPassword= true thì đổi mật khẩu
		// mới
		if (isPassword == true) {
			shipperService.save(shipper); // Lưu shipper
		}else {
			model.addAttribute("errorMessage", "Mật khẩu cũ không chính xác. Vui lòng thử lại!");
		    return "views/shipper/editProfile";
		}
		return "redirect:/shipper/profile/" + IDShipper;

	}

	/**
	 * @PostMapping("/shipper/profile/update/{id}") public String
	 * updateProfile(@PathVariable("id") String shipperID, ModelMap model,
	 * HttpServletRequest req,
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam("phone") String phone,
	 * 
	 * @RequestParam("address") String address,
	 * 
	 * @RequestParam("city") String city,
	 * 
	 * @RequestParam("statusShipper") Boolean statusShipper,
	 * 
	 * @RequestParam("oldPassword") String oldPassword,
	 * 
	 * @RequestParam(value = "newPassword", required = false) String newPassword)
	 *                     throws IOException {
	 * 
	 *                     // Lấy thông tin Shipper từ cơ sở dữ liệu Shipper shipper
	 *                     = shipperService.findByID(shipperID) .orElseThrow(() ->
	 *                     new RuntimeException("Shipper not found with ID: " +
	 *                     shipperID));
	 * 
	 *                     // Kiểm tra mật khẩu cũ if
	 *                     (!shipper.getPassword().equals(oldPassword)) { throw new
	 *                     RuntimeException("Current password is incorrect"); }
	 * 
	 *                     // Nếu có mật khẩu mới, cập nhật if (newPassword != null
	 *                     && !newPassword.isEmpty()) {
	 *                     shipper.setPassword(newPassword); }
	 * 
	 *                     // Các thông tin khác shipper.setName(name);
	 *                     shipper.setPhone(phone); shipper.setAddress(address);
	 *                     shipper.setCity(city);
	 *                     shipper.setStatusShipper(statusShipper);
	 * 
	 *                     // Xử lý ảnh đại diện (giống phần code trước) if
	 *                     (!newPicture.isEmpty()) { // Tìm shipper cũ Shipper
	 *                     oldShipper = shipperService.findByID(shipperID)
	 *                     .orElseThrow(() -> new RuntimeException("Shipper not
	 *                     found with ID: " + shipperID));
	 * 
	 *                     // Hình cũ String fileOld = oldShipper.getPicture();
	 * 
	 *                     // Link từ request String images =
	 *                     req.getParameter("images");
	 * 
	 *                     // Xử lý ảnh String fname = ""; String uploadPath =
	 *                     ConstantUtil.UPLOAD_PATH; File uploadDir = new
	 *                     File(uploadPath); if (!uploadDir.exists()) {
	 *                     uploadDir.mkdir(); }
	 * 
	 *                     try { Part part = req.getPart("images1"); if
	 *                     (part.getSize() > 0) { String filename =
	 *                     Paths.get(part.getSubmittedFileName()).getFileName().toString();
	 *                     String ext = filename.substring(filename.lastIndexOf(".")
	 *                     + 1); fname = System.currentTimeMillis() + "." + ext;
	 *                     part.write(uploadPath + "/" + fname);
	 *                     oldShipper.setPicture(fname); } else if (images != null)
	 *                     { oldShipper.setPicture(images); } else {
	 *                     oldShipper.setPicture(fileOld != null ? fileOld :
	 *                     "avatar.jpg"); } } catch (Exception e) {
	 *                     e.printStackTrace(); }
	 * 
	 *                     // Lưu thông tin Shipper đã cập nhật
	 *                     shipperService.editProfile(shipper);
	 * 
	 *                     return "redirect:/shipper/profile/" + shipperID; }
	 * 
	 ***/

	/**
	 * @PostMapping("/shipper/profile/update/{id}") public String
	 * updateProfile(@PathVariable("id") String shipperID, ModelMap model,
	 * HttpServletRequest req, @ModelAttribute("shipper") Shipper
	 * shipper, @RequestParam("oldPassword") String oldPassword,
	 * 
	 * @RequestParam(required = false) String newPassword) throws IOException {
	 * 
	 *                        // Tìm shipper cũ Shipper oldShipper =
	 *                        shipperService.findByID(shipperID) .orElseThrow(() ->
	 *                        new RuntimeException("Shipper not found with ID: " +
	 *                        shipperID));
	 * 
	 *                        // Hình cũ String fileOld = oldShipper.getPicture();
	 * 
	 *                        // Link từ request String images =
	 *                        req.getParameter("images");
	 * 
	 *                        // Xử lý ảnh String fname = ""; String uploadPath =
	 *                        ConstantUtil.UPLOAD_PATH; File uploadDir = new
	 *                        File(uploadPath); if (!uploadDir.exists()) {
	 *                        uploadDir.mkdir(); }
	 * 
	 *                        try { Part part = req.getPart("images1"); if
	 *                        (part.getSize() > 0) { String filename =
	 *                        Paths.get(part.getSubmittedFileName()).getFileName().toString();
	 *                        String ext =
	 *                        filename.substring(filename.lastIndexOf(".") + 1);
	 *                        fname = System.currentTimeMillis() + "." + ext;
	 *                        part.write(uploadPath + "/" + fname);
	 *                        oldShipper.setPicture(fname); } else if (images !=
	 *                        null) { oldShipper.setPicture(images); } else {
	 *                        oldShipper.setPicture(fileOld != null ? fileOld :
	 *                        "avatar.jpg"); } } catch (Exception e) {
	 *                        e.printStackTrace(); }
	 * 
	 *                        // Kiểm tra mật khẩu cũ if
	 *                        (!oldPassword.equals(oldShipper.getPassword())) {
	 *                        throw new RuntimeException("Current password is
	 *                        incorrect"); }
	 * 
	 *                        // Nếu có mật khẩu mới, cập nhật if (newPassword !=
	 *                        null && !newPassword.isEmpty()) {
	 *                        oldShipper.setPassword(newPassword); }
	 * 
	 *                        // Các thông tin khác
	 *                        oldShipper.setName(shipper.name);
	 *                        oldShipper.setPhone(shipper.phone);
	 *                        oldShipper.setAddress(shipper.address);
	 *                        oldShipper.setCity(shipper.city);
	 *                        oldShipper.setStatusShipper(shipper.statusShipper);
	 * 
	 *                        shipperService.save(oldShipper);
	 * 
	 *                        return "redirect:/shipper/profile/" + shipperID; }
	 ***/
}
