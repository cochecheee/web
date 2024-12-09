package com.example.project.service;

import java.util.Optional;

import com.example.project.entity.TransportType;

public interface IGoodsTypeService {

	long count();

	Optional<TransportType> findById(String id);

	<S extends TransportType> S save(S entity);

}
