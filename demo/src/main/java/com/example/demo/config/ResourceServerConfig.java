//package com.example.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests()
//				.antMatchers("/login**", "/oauth/token", "/h2-console").permitAll()
//				.antMatchers("/api/**").authenticated();
//	}
//
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService())
////			.passwordEncoder(new NoOpPasswordEncoder());	
////
////		super.configure(auth);
////	}
////	
////	protected class NoOpPasswordEncoder implements PasswordEncoder{
////		@Override
////		public boolean matches(CharSequence rawPassword, String encodedPassword) {				
////			return rawPassword.toString().equals(encodedPassword);
////		}
////		
////		@Override
////		public String encode(CharSequence rawPassword) {
////			return rawPassword.toString();
////		}
////	}
//}
