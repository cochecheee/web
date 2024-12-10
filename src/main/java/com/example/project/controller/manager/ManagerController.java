package com.example.project.controller.manager;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.project.dto.CustomerRequestDTO;
import com.example.project.dto.ShipperDTO;
import com.example.project.entity.Customer;
import com.example.project.entity.Manager;
import com.example.project.entity.Order;
import com.example.project.entity.PostOffice;
import com.example.project.entity.Shipper;
import com.example.project.service.ICustomerService;
import com.example.project.service.IOrderService;
import com.example.project.service.IShipperService;
import com.example.project.service.impl.CustomerServiceImpl;
import com.example.project.service.impl.ManagerServiceImpl;
import com.example.project.service.impl.OrderServiceImpl;
import com.example.project.service.impl.ShipperServiceImpl;
import com.example.project.utils.ConstantUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

@Controller
public class ManagerController {
	@Autowired
	ManagerServiceImpl managerService;
	@Autowired
	OrderServiceImpl orderService;
	@Autowired
	CustomerServiceImpl customerService;
	@Autowired
	ShipperServiceImpl shipperService; 

	@GetMapping("/manager/{id}")
	public String managerHome(@PathVariable("id") String id, ModelMap model) {
		// Tìm Manager theo ID
		Manager manager = managerService.findById(id).get();

		// Lấy danh sách Customer
		List<Customer> customers = manager.getUsers();
		// Truyền thông tin vào model
		model.addAttribute("manager", manager);
		model.addAttribute("customers", customers);
		model.addAttribute("managerID", id);

		// id post
		String postid = manager.getPost().getIDPost();
		System.out.println(postid);
//	    
		List<Order> orders = orderService.filterOrderByPostID(postid);
		// System.out.println(orders);
		model.addAttribute("orders", orders);

		List<Shipper> shippers = manager.getShippers();
		model.addAttribute("shippers", shippers);

		return "views/manager/manager-home";
	}
	
	@GetMapping("/manager/customers")
	public String customerDetails(ModelMap model) {
		List<Customer> customers = customerService.findAll();
		
		model.addAttribute("customers", customers);
		
		return "views/manager/customer-details";
	}

	@GetMapping("/manager/customer/edit/{id}")
	public String editCustomer(@PathVariable("id") String customerID, ModelMap model) {
		Customer customer = customerService.findById(customerID).get();
		
		model.addAttribute("customer", customer);
		model.addAttribute("managerID", customer.getManager().getIDManager());
		
		return "views/manager/customer-edit";
	}

	/*
	 * @PostMapping("/manager/customer/edit/{id}") public String editCustomer(
	 * 
	 * @PathVariable("id") String customerID, ModelMap model,
	 * 
	 * @RequestParam("managerID") String managerID, HttpServletRequest req,
	 * 
	 * @ModelAttribute("customer") Customer customer) {
	 * 
	 * Manager manager = managerService.findById(managerID).get();
	 * customer.setManager(manager);
	 * 
	 * // Tìm customer cũ Customer oldCustomer =
	 * customerService.findById(customerID) .orElseThrow(() -> new
	 * RuntimeException("Customer not found with ID: " + customerID));
	 * 
	 * // Hình cũ String fileOld = oldCustomer.getPicture();
	 * 
	 * // Link từ request String images = req.getParameter("images");
	 * 
	 * // Xử lý images String fname = ""; String uploadPath =
	 * ConstantUtil.UPLOAD_PATH; File uploadDir = new File(uploadPath); if
	 * (!uploadDir.exists()) { uploadDir.mkdir(); }
	 * 
	 * try { Part part = req.getPart("images1"); if (part.getSize() > 0) { String
	 * filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	 * String ext = filename.substring(filename.lastIndexOf(".") + 1); fname =
	 * System.currentTimeMillis() + "." + ext; part.write(uploadPath + "/" + fname);
	 * customer.setPicture(fname); } else if (images != null) {
	 * customer.setPicture(images); } else { customer.setPicture(fileOld); } } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * customerService.save(customer);
	 * 
	 * return "redirect:/manager/" + managerID; }
	 */
	
	//shipper
	@GetMapping("/manager/shippers")
	public String shipperDetails(ModelMap model, @RequestParam("managerID") String managerID) {
		Manager manager = managerService.findById(managerID).get();
		
		List<Shipper> shippers = manager.getShippers();
		if(shippers == null) {
			System.out.println(managerID);
			System.out.println("Shipper rỗng");
		}
		
		model.addAttribute("shippers", shippers);
		
		return "views/manager/shipper-details";
	}
	
	@GetMapping("manager/shipper/delete/{id}")
	public String deleteShipper(ModelMap model, @PathVariable("id") String shipperID) {
		// tim shipper
		Shipper shipper = shipperService.findByID(shipperID).get();
		System.out.println(shipperID);
		// lấy thong tin manager
		String managerID = shipper.getManager().getIDManager();
		
		// xóa shipper
		shipperService.deleteById(shipperID);
		
		// điều hướng về
		return "redirect:/manager/shippers?managerID=" + managerID;
	}
	
	@GetMapping("manager/shipper/add")
	public String addShipper(ModelMap model, @RequestParam("managerID") String managerID) {
		Manager manager = managerService.findById(managerID).get();
		PostOffice post = manager.getPost();
		
		model.addAttribute("posts", post);
		model.addAttribute("managers", manager);
		model.addAttribute("shipperDTO", new ShipperDTO());
		
		return "views/manager/addOrEdit";
	}
	
	@PostMapping("manager/shipper/add")
	public String submitAddShipper(@ModelAttribute ShipperDTO shipperDTO,
												HttpServletRequest req) {
		
		String managerID = shipperDTO.getManagerId();
		String postID = shipperDTO.getPostId();
		Manager manager = managerService.findById(managerID).get();
		PostOffice post = manager.getPost();
		
		Shipper newShipper = new Shipper();
		newShipper.setManager(manager);
		newShipper.setPost(post);
		
		newShipper.setName(shipperDTO.getName());
		newShipper.setCccd(shipperDTO.getCccd());
		newShipper.setPhone(shipperDTO.getPhone());
		newShipper.setGender(shipperDTO.getGender());
//		private Date birth;
//	    private String address;
//	    private String city;
//	    private String picture; // Hình ảnh có thể được lưu trữ dưới dạng URL hoặc tên tệp.
//	    private String username;
//	    private String password;
//	    private Boolean statusShipper;
		newShipper.setAddress(shipperDTO.getAddress());
		newShipper.setUsername(shipperDTO.getUsername());
		newShipper.setPassword(shipperDTO.getPassword());
		//newShipper.setBirth(shipperDTO.getBirth());
		newShipper.setStatusShipper(shipperDTO.getStatusShipper());
		//newShipper.setBirth(null);
		
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
	            newShipper.setPicture(fname);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	    shipperService.save(newShipper);
	    
	    return "redirect:/manager/" + managerID;
		
	}

}
