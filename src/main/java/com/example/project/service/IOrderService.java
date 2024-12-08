package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.Order;

public interface IOrderService {

	Optional<Order> findById(String id);

	List<Order> findAll();

}