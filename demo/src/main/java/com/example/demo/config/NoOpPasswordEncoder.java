package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class NoOpPasswordEncoder implements PasswordEncoder{
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {				
		return rawPassword.toString().equals(encodedPassword);
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}
}