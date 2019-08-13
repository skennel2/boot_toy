package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 인증서버 구성
 * 
 * @author skennel
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private AuthenticationManager authenticationManager;
	
	private TokenStore tokenStore;
	
	private PasswordEncoder passwordEncoder;
	
	private UserDetailsService userDetailsService;  
			
	@Autowired
	public AuthServerConfig(AuthenticationManager authenticationManager, TokenStore tokenStore,
			PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.tokenStore = tokenStore;
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * 클라이언트 설정 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
				.withClient("yunsu_client") // 클라이언트를 등록한다.
				.secret(passwordEncoder.encode("{noop}yunsu_secret")) // 클라이언트의 secret을 지정한다.
				.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
				.accessTokenValiditySeconds(5000) // 액세스토큰이 얼마의 시간동안 유효한가?
				.refreshTokenValiditySeconds(15000)
				.scopes("read", "write", "trust");			
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.tokenStore(tokenStore);		
	}
	
}
