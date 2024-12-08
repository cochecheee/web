package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.project.entity.Manager;

public interface IManagerService {

	long count();

	Optional<Manager> findById(String id);

	List<Manager> findAll();

	Page<Manager> findAll(Pageable pageable);

	<S extends Manager> S save(S entity);

	Manager findByUsername(String username);

}
