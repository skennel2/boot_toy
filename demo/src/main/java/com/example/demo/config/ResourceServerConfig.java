package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * 실제 REST API에 대한 인증을 담당  
 * @author skennel
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {	
		resources
			.resourceId("resorce_id")
			.stateless(false);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.anonymous().disable()			
			.authorizeRequests()
				.antMatchers("/login**", "/oauth/token", "/h2-console").permitAll()
				.antMatchers("/api/**").authenticated()
				.and()
			.exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler());	
	}
}
