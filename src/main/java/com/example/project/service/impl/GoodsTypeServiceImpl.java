package com.example.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.TransportType;
import com.example.project.repository.IGoodsTypeRepository;
import com.example.project.service.IGoodsTypeService;

@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService{
	@Autowired
	IGoodsTypeRepository goodsRepository;
	
	public GoodsTypeServiceImpl(IGoodsTypeRepository goodsRepository) {
		this.goodsRepository = goodsRepository;
	}

	@Override
	public <S extends TransportType> S save(S entity) {
		return goodsRepository.save(entity);
	}

	@Override
	public Optional<TransportType> findById(String id) {
		return goodsRepository.findById(id);
	}

	@Override
	public long count() {
		return goodsRepository.count();
	}
	
	
}
