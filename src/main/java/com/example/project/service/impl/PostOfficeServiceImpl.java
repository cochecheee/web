package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.PostOffice;
import com.example.project.repository.IPostOfficeRepository;
import com.example.project.service.IPostOfficeService;

@Service
public class PostOfficeServiceImpl implements IPostOfficeService{
	
	@Autowired
	IPostOfficeRepository postRepository;
	
	public PostOfficeServiceImpl(IPostOfficeRepository postOfficeRepository) {
		this.postRepository = postOfficeRepository;
	}

	public List<PostOffice> findPostOfficeByCity(String cityName) {
		return postRepository.findPostOfficeByCity(cityName);
	}

	public PostOffice findPostOfficeByName(String postName) {
		return postRepository.findPostOfficeByName(postName);
	}

	public Optional<PostOffice> findById(String id) {
		return postRepository.findById(id);
	}
	
	
	
	
}
