package com.example.demo.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.article.domain.Account;
import com.example.demo.article.dto.AccountJoinRequest;
import com.example.demo.article.repository.AccountRepository;

@RestController
@RequestMapping(path = "/api/account")
public class AccountRestController {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> options(){
		return ResponseEntity
				.ok()
				.allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.POST, HttpMethod.PUT)
				.build();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Account> getById(@PathVariable Long id){
		return accountRepo.findById(id)
				.map(account -> ResponseEntity.ok(account))
				.orElseThrow(() ->new RuntimeException());		
	}
	
	@GetMapping(path = "/byloginid/{id}")
	public ResponseEntity<Account> getById(@PathVariable String id){
		return accountRepo.findByLoginId(id)
				.map(account -> ResponseEntity.ok(account))
				.orElseThrow(() ->new RuntimeException());		
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<List<Account>> getAll(){
		return ResponseEntity.ok(accountRepo.findAll());
	}
	
	@PutMapping("/join")
	public ResponseEntity<Void> join(@RequestBody AccountJoinRequest request){
		Account newAccount = new Account(request.getLoginId(), request.getPassword());
		accountRepo.save(newAccount);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		accountRepo.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAll(){		
		accountRepo.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
