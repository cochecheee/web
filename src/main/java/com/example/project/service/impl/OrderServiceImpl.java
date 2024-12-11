package com.example.project.service.impl;

import java.util.List;
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

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public <S extends Order> S save(S entity) {
		return orderRepository.save(entity);
	}

	@Override
	public List<Order> findByIdShipperAndStatus(String IDShipper, Integer status) {
		return orderRepository.findByShipper_IDShipperAndStatus(IDShipper, status);
	}

	@Override
	public List<Order> findByIdShipper(String IDShipper) {
		return orderRepository.findByShipper_IDShipper(IDShipper);
	}

	@Override
	public Long countByIdShipperAndStatus(String IDShipper, Integer status) {
		return orderRepository.countByShipper_IDShipperAndStatus(IDShipper, status);
	}

	public List<Order> filterOrderByPostID(String postID) {
		return orderRepository.filterOrderByPostID(postID);

	}

	
	
}
