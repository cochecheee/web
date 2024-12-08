package com.example.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Order;
import com.example.project.repository.IOrderRepository;
import com.example.project.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	IOrderRepository orderRepository;
	
	public OrderServiceImpl(IOrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Optional<Order> findById(String id) {
		return orderRepository.findById(id);
	}

	
	
}
