package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.AccountRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		return accountRepository.findByLoginId(loginId)
				.map(acct -> new User(acct.getLoginId(), acct.getPassword(), Arrays.asList(new SimpleGrantedAuthority("Admin"))))
				.orElseThrow(() -> new RuntimeException());
	}

}
