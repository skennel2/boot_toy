package com.example.demo.article.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Account extends EntityBase {

	@Column(name = "login_id", unique = true, length = 30)
	private String loginId;

	@Column(name = "password", unique = false, length = 50)
	private String password;

	public Account(Long id, String loginId, String password) {
		super();
		this.setId(id);
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

	public String getLoginId() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Account [loginId=" + loginId + ", password=" + password + "]";
	}

}
