package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{
	 @Query("SELECT a FROM Account a WHERE a.username = :username")
	 Account findAccountByUserName(@Param("username") String username);
	
}
