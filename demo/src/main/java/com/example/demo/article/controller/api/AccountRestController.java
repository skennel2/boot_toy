package com.example.demo.article.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.article.domain.Account;
import com.example.demo.article.domain.exception.AccountNotFoundException;
import com.example.demo.article.dto.AccountJoinRequest;
import com.example.demo.article.service.AccountService;

@RestController
@RequestMapping(path = "/api/account")
public class AccountRestController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(method = RequestMethod.OPTIONS)
	public ResponseEntity<?> options() {
		return ResponseEntity.ok()
				.allow(HttpMethod.GET, HttpMethod.DELETE, HttpMethod.OPTIONS, HttpMethod.POST, HttpMethod.PUT).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Account> getById(@PathVariable Long id) {
		return ResponseEntity.ok(accountService.getById(id));
	}

	@GetMapping(path = "/byloginid/{id}")
	public ResponseEntity<Account> getByLoginId(@PathVariable String loginId) {
		return ResponseEntity.ok(accountService.getByLoginId(loginId));
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<Account>> getAll() {
		return ResponseEntity.ok(accountService.getAll());
	}

	@PutMapping("/join")
	public ResponseEntity<Void> join(@RequestBody AccountJoinRequest request) {
		request.setPassword("{noop}".concat(request.getPassword()));
		accountService.add(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		accountService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<?> notFound() {
		return ResponseEntity.noContent().build();
	}
}
