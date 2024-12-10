package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.GoodsType;
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
	public <S extends GoodsType> S save(S entity) {
		return goodsRepository.save(entity);
	}

	@Override
	public List<GoodsType> findAll() {
		return goodsRepository.findAll();
	}

	@Override
	public Optional<GoodsType> findById(String id) {
		return goodsRepository.findById(id);
	}

	@Override
	public long count() {
		return goodsRepository.count();
	}

	
}
