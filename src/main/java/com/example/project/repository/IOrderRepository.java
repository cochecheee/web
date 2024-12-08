package com.example.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, String>{
}
