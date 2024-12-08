package com.example.project.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.project.entity.Manager;
import com.example.project.repository.IManagerRepository;
import com.example.project.service.IManagerService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ManagerServiceImpl implements IManagerService{
	@Autowired
	private IManagerRepository managerRepository;
	@PersistenceContext
    private EntityManager entityManager;
	
	public ManagerServiceImpl(IManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}

	@Override
	public <S extends Manager> S save(S entity) {
		return managerRepository.save(entity);
	}

	@Override
	public Page<Manager> findAll(Pageable pageable) {
		return managerRepository.findAll(pageable);
	}

	@Override
	public List<Manager> findAll() {
		return managerRepository.findAll();
	}

	@Override
	public Optional<Manager> findById(String id) {
		return managerRepository.findById(id);
	}

	@Override
	public long count() {
		return managerRepository.count();
	}

	@Override
	public Manager findByUsername(String username) {
	    String jpql = "SELECT m FROM Manager m LEFT JOIN FETCH m.vouchers WHERE m.username = :username";
	    return entityManager.createQuery(jpql, Manager.class)
	                        .setParameter("username", username)
	                        .getSingleResult();
	}
	
	
}
