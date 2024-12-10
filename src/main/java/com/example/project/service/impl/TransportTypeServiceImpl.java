package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.TransportType;
import com.example.project.repository.ITransportTypeRepository;
import com.example.project.service.ITransportTypeService;


@Service
public class TransportTypeServiceImpl implements ITransportTypeService{
	@Autowired 
	private ITransportTypeRepository transportRepository;
	
	public TransportTypeServiceImpl(ITransportTypeRepository transportTypeRepository) {
		this.transportRepository = transportTypeRepository;
	}

	@Override
	public <S extends TransportType> S save(S entity) {
		return transportRepository.save(entity);
	}

	@Override
	public Optional<TransportType> findById(String id) {
		return transportRepository.findById(id);
	}

	@Override
	public long count() {
		return transportRepository.count();
	}

	public List<TransportType> findAll() {
		return transportRepository.findAll();
	}
	
	
}
