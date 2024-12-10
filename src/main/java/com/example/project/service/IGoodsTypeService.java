package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.GoodsType;

public interface IGoodsTypeService {

	long count();

	Optional<GoodsType> findById(String id);

	List<GoodsType> findAll();

	<S extends GoodsType> S save(S entity);

}
