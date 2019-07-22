package com.example.demo.config;

//@Configuration
//@EnableAuthorizationServer
//public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager);
//	}
//	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.checkTokenAccess("isAuthenticated()");
//	}
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("foo")
//			.authorizedGrantTypes("client_creditials", "password")
//			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//			.scopes("read", "write", "trust")
//			.resourceIds("oauth2-resource")
//			.accessTokenValiditySeconds(500)
//			.secret("bar");
//	}
//}
