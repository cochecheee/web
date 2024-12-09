package com.example.project.service;

import java.util.List;

import com.example.project.entity.Account;

public interface IAccountService {

	long count();

	List<Account> findAll();

	<S extends Account> S save(S entity);

	Account findAccountByUserName(String username);

}
