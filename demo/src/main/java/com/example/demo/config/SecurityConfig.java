package com.example.demo.config;

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
		http.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/login**", "/oauth/token", "/h2-console").permitAll()
				.antMatchers("/api/**").permitAll();//.authenticated();
//				.and()
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//			.formLogin();
//			.httpBasic()
//				.and()
//			.oauth2Login()
//				.;
//		
		// 매번 Request 마다 ID:PWD를 전송하기 때문에 SSL(HTTPS) 사용이 필수
		// 8443이 SSL 포트일 경우 사용시
		//http.portMapper().http(8080).mapsTo(8443);
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
