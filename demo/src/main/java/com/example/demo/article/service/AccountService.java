package com.example.demo.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.exception.AccountNotFoundException;
import com.example.demo.article.dto.AccountJoinRequest;
import com.example.demo.article.repository.AccountRepository;

@Service
public class AccountService {

	private AccountRepository accountRepo;
	
	@Autowired
	public AccountService(AccountRepository accountRepo) {
		this.accountRepo = accountRepo;
	}

	public Account getById(Long id) {
		return accountRepo.findById(id)
				.orElseThrow(() -> new AccountNotFoundException());
	}

	public Account getByLoginId(String loginId) {
		return accountRepo.findByLoginId(loginId)
				.orElseThrow(() -> new AccountNotFoundException());
	}

	public List<Account> getAll() {
		return accountRepo.findAll();
	}

	public void add(AccountJoinRequest request) {
		Account newAccount = new Account(request.getLoginId(), request.getPassword());
		accountRepo.save(newAccount);
	}

	public void deleteById(@PathVariable Long id) {
		accountRepo.deleteById(id);
	}
}
