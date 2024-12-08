package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Customer;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer	, String>{
	@Query("SELECT a FROM Customer a WHERE a.username = :username")
	Customer findByUserName(String username);
}
