package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.TransportType;

@Repository
public interface ITransportTypeRepository extends JpaRepository<TransportType, String>{

}
