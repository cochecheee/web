package com.example.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.repository.IAccountRepository;
import com.example.project.service.IAccountService;

@Service 
public class AccountServiceImpl implements IAccountService{
	@Autowired
	IAccountRepository accountRepository;
	
	public AccountServiceImpl(IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
}
