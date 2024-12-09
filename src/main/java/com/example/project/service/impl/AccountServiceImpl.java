package com.example.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.Account;
import com.example.project.repository.IAccountRepository;
import com.example.project.service.IAccountService;

@Service 
public class AccountServiceImpl implements IAccountService{
	@Autowired
	IAccountRepository accountRepository;
	
	public AccountServiceImpl(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account findAccountByUserName(String username) {
		return accountRepository.findAccountByUserName(username);
	}

	@Override
	public <S extends Account> S save(S entity) {
		return accountRepository.save(entity);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public long count() {
		return accountRepository.count();
	}
	
	
}
