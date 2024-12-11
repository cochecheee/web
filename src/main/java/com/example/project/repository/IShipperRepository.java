package com.example.project.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.entity.Shipper;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipperRepository extends JpaRepository<Shipper, String> {
	Optional<Shipper> findByName(String name);
	
	Optional<Shipper> findByPost_IDPost(String IDPost);
	
	//@Query("SELECT o FROM Order o WHERE o.IDShipper = :IDShipper")
    //List<Order> findOrdersByShipperId(@Param("IDShipper") String IDShipper);
	
	//@Query("SELECT o FROM Order o WHERE o.IDShipper = :IDShipper AND o.status = :status")
	//List<Order> findOrdersByShipperIdAndStatus(@Param("IDShipper") String IDShipper, @Param("status") Integer status);

}
