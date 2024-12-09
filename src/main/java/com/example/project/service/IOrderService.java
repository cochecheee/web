package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.Order;

public interface IOrderService {

	Optional<Order> findById(String id);

	List<Order> findAll();

	List<Order> findByIdShipperAndStatus(String IDShipper, Integer status);

	List<Order> findByIdShipper(String IDShipper);
	
	Long countByIdShipperAndStatus(String IDShipper, Integer status);
}
