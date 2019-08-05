package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 참고자료
 * https://www.popit.kr/spring-security-ajax-%ED%98%B8%EC%B6%9C-%EC%8B%9C-csrf-%EA%B4%80%EB%A0%A8-403-forbidden-%EC%97%90%EB%9F%AC/
 * @author skennel
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() { 
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		return source;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.configurationSource(corsConfigurationSource())
				.and()
			.csrf()
				.disable()
			.anonymous()
				.disable()
			.authorizeRequests()
				//.antMatchers("/login**", "/oauth/token", "/h2-console").permitAll()
				.antMatchers("/api/**").authenticated();
		// 매번 Request 마다 ID:PWD를 전송하기 때문에 SSL(HTTPS) 사용이 필수
		// 8443이 SSL 포트일 경우 사용시
		//http.portMapper().http(8080).mapsTo(8443);
	}	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
			.passwordEncoder(passwordEncoder);	

		super.configure(auth);
	}
}
