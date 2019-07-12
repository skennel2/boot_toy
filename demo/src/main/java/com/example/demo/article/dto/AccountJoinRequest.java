package com.example.demo.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountJoinRequest {
	@JsonProperty(value = "login_id")
	private String loginId;
	
	@JsonProperty(value = "password")
	private String password;
	
	@JsonProperty(value = "password_check")
	private String passwordCheck;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	@Override
	public String toString() {
		return "AccountJoinRequest [loginId=" + loginId + ", password=" + password + ", passwordCheck=" + passwordCheck
				+ "]";
	}

}
