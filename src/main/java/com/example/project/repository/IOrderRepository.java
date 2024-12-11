package com.example.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, String>{
	// Tìm danh sách Order theo Shipper ID và Status
	@Query("SELECT o FROM Order o WHERE o.shipper.IDShipper = :shipperId AND o.status = :status")
    List<Order> findByShipper_IDShipperAndStatus(@Param("shipperId") String shipperId, @Param("status") Integer status);
    
    // Tìm danh sách Order theo Shipper ID
    List<Order> findByShipper_IDShipper(String shipperId);
    
    // Đếm số lượng Order theo Shipper ID và Status
    Long countByShipper_IDShipperAndStatus(String shipperId, Integer status);

	 @Query("SELECT o FROM Order o WHERE o.postOffice.IDPost = :postID")
	 List<Order> filterOrderByPostID(@Param("postID") String postID);
	 
}
