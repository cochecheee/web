package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Customer;
import com.example.project.repository.ICustomerRepository;
import com.example.project.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	ICustomerRepository customerRepository;
	
	public CustomerServiceImpl(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer findByUserName(String username) {
		return customerRepository.findByUserName(username);
	}

	@Override
	public <S extends Customer> S save(S entity) {
		return customerRepository.save(entity);
	}

	@Override
	public Optional<Customer> findById(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
}
