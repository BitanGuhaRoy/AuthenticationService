package org.example.authenticationservice;

import org.example.authenticationservice.security.repositories.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
class AuthenticationServiceApplicationTests {
@Autowired
	private JpaRegisteredClientRepository jpaRegisteredClientRepository;
	@Test
	void contextLoads() {
	}

@Test
@Commit
	public  void addRegisteredClient() {


//		RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("$2a$12$mpoIrggRomWLZQYyQMEvB.38dNmfZPmIjP2DvOfZ4Qt6QEhonqEKS")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("ADMIN")
//                .scope("SELLER")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//		jpaRegisteredClientRepository.save(oidcClient);


    }

}
