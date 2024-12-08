package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.PostOffice;

@Repository
public interface IPostOfficeRepository extends JpaRepository<PostOffice, String>{
	
	@Query("SELECT p FROM PostOffice p WHERE p.postCity = :cityName")
    List<PostOffice> findPostOfficeByCity(@Param("cityName") String cityName);
}
