package com.example.project.controller.shipper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.project.service.impl.OrderServiceImpl;
import com.example.project.service.impl.ShipperServiceImpl;

public class ShipperController {
	@Autowired
	private ShipperServiceImpl shipperService;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	
}
