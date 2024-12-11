package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Order;
import com.example.project.entity.Rate;
import com.example.project.entity.Shipper;
import com.example.project.repository.IOrderRepository;
import com.example.project.repository.IRateRepository;
import com.example.project.repository.IShipperRepository;
import com.example.project.service.IShipperService;

@Service
public class ShipperServiceImpl implements IShipperService {


	@Autowired
	IShipperRepository shipperRepository;
	
	@Autowired
	IOrderRepository orderRepository;
	
	@Autowired
	IRateRepository rateRepository;
	
	@Override
	public Optional<Shipper> findByName(String name) {
		// TODO Auto-generated method stub
		return shipperRepository.findByName(name);
	}

	@Override
	public Optional<Shipper> findByID(String IDShipper) {
		// TODO Auto-generated method stub
		return shipperRepository.findById(IDShipper);
	}
	

	public void deleteById(String id) {
		shipperRepository.deleteById(id);
	}


	@Override
	public List<Order> findAllOrder(String IDShipper) {
		// TODO Auto-generated method stub
		return orderRepository.findByShipper_IDShipper(IDShipper) ;
	}

	@Override
	public void editProfile(Shipper shipper) {
		shipperRepository.save(shipper);
	}

	@Override
	public void editStatusOrder(String IDOrder, Integer status) {
		Order order = orderRepository.findById(IDOrder).orElse(null);
		
		order.setStatus(status);
		orderRepository.save(order);
	}

	@Override
	public Optional<Shipper> findByPost(String IDPost) {
		return shipperRepository.findByPost_IDPost(IDPost);
	}

	@Override
	public List<Order> findAllOrderBystatus(String IDShipper, Integer status) {
		return orderRepository.findByShipper_IDShipperAndStatus(IDShipper, status);
	}


	@Override
	public Optional<Order> findOrderByIDOrder(String IDOrder) {
		// TODO Auto-generated method stub
		return orderRepository.findById(IDOrder);
	}

	@Override
	public int StarRateShipper(String IDShipper) {
		List<Rate> rates = rateRepository.findByShipper_IDShipper(IDShipper);
		
		// Kiểm tra nếu danh sách trống
	    if (rates.isEmpty()) {
	        return 0; // Không có đánh giá nào
	    }
	    
	 // Tính trung bình cộng của thuộc tính 'star'
	    double average = rates.stream()
	                          .mapToInt(Rate::getStar) // Lấy giá trị star
	                          .average() // Tính trung bình cộng
	                          .orElse(0.0); // Giá trị mặc định nếu không có phần tử nào
	    
	    return (int) Math.round(average); // Trả về trung bình cộng làm tròn
	}


	public void save(Shipper shipper) {
		shipperRepository.save(shipper);
	}


}
