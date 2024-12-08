package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Manager;

@Repository

public interface IManagerRepository extends JpaRepository<Manager, String>{
	
	Manager findByUsername(String username);

}
