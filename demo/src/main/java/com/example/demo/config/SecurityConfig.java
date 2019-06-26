package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.repository.AccountRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AccountRepository accountRepositiry;
	
	@Bean
	UserDetailsService userDetailService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
				return accountRepositiry.findByLoginId(loginId)
						.map(acct -> new User(acct.getLoginId(), acct.getPassword(), Arrays.asList(new SimpleGrantedAuthority("Admin"))))
						.orElseThrow(() -> new RuntimeException());
			}
		}; 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/account/**").permitAll()
			.antMatchers("/article/**").authenticated()
			.and()
			.csrf();
			
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
		super.configure(auth);
	}
}
