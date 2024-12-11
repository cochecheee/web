package com.example.project.service;

import java.util.List;
import java.util.Optional;


import com.example.project.entity.Order;
import com.example.project.entity.Shipper;


import org.springframework.stereotype.Service;


@Service
public interface IShipperService {
	public Optional<Shipper> findByName(String name);
	public Optional<Shipper> findByID(String IDShipper);
	public Optional<Order> findOrderByIDOrder(String IDOrder);
	public List<Order> findAllOrder(String IDShipper);
	public int StarRateShipper(String IDShipper);
	
	//Cái này để tìm đơn theo trạng thái của nó
		// Ví dụ tìm đon chưa giao
		//Hay đơn đã giao
	public List<Order> findAllOrderBystatus(String IDShipper, Integer status);
	public Optional<Shipper> findByPost(String IDPost);
	
	void editProfile(Shipper shipper);
	
	void editStatusOrder(String IDOrder, Integer status);
	
}
