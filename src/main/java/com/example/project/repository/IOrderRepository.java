package com.example.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, String>{
	 @Query("SELECT o FROM Order o WHERE o.postOffice.IDPost = :postID")
	 List<Order> filterOrderByPostID(@Param("postID") String postID);
}
