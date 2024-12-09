package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.Customer;

public interface ICustomerService {

	List<Customer> findAll();

	Optional<Customer> findById(String id);

	<S extends Customer> S save(S entity);

	Customer findByUserName(String username);

}
