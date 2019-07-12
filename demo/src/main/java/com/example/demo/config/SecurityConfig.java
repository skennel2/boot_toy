package com.example.demo.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 참고자료
 * https://www.popit.kr/spring-security-ajax-%ED%98%B8%EC%B6%9C-%EC%8B%9C-csrf-%EA%B4%80%EB%A0%A8-403-forbidden-%EC%97%90%EB%9F%AC/
 * @author skennel
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		 		.antMatchers("/api/account/**").authenticated()
			.and()
				.csrf()
					.disable()
				.httpBasic();
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(new NoOpPasswordEncoder());	
		
		super.configure(auth);
	}
	
	protected class NoOpPasswordEncoder implements PasswordEncoder{
		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {				
			return rawPassword.toString().equals(encodedPassword);
		}
		
		@Override
		public String encode(CharSequence rawPassword) {
			return rawPassword.toString();
		}
	}
}
