package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "login_id", unique = true, length = 30)
	private String loginId;

	@Column(name = "password", unique = false, length = 50)
	private String password;

	public Account(Long id, String loginId, String password) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
	}
	
	public Account(String loginId, String password) {
		super();	
		this.loginId = loginId;
		this.password = password;
	}

	protected Account() {
	}

	public Long getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}
}
