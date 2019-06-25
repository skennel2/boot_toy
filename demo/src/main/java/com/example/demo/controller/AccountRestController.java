package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Account;
import com.example.demo.repository.AccountRepositiry;

@RestController
@RequestMapping(path = "/account")
public class AccountRestController {
	
	@Autowired
	private AccountRepositiry accountRepositiry;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Account> getByLoginId(@PathVariable Long id){
		return accountRepositiry.findById(id)
				.map(account -> new ResponseEntity<>(account, HttpStatus.OK))
				.orElseThrow(() ->new RuntimeException());		
	}
}
