package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.entity.PostOffice;

public interface IPostOfficeService {

	Optional<PostOffice> findById(String id);

	PostOffice findPostOfficeByName(String postName);

	List<PostOffice> findPostOfficeByCity(String cityName);

}
