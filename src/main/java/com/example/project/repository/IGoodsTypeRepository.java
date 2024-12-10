package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.entity.GoodsType;

@Repository
public interface IGoodsTypeRepository extends JpaRepository<GoodsType, String>{

}
