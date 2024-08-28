package com.clientv4.clientv4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/signup"))
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/signup").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/myoauth2"))
                .oauth2Client(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(myoauth2ClientRegistration());
    }

    private ClientRegistration myoauth2ClientRegistration() {
        return ClientRegistration.withRegistrationId("myoauth2")
                .issuerUri("http://auth.localhost:9000")
                .clientId("client")
                .clientSecret("secret")
                .scope(OidcScopes.OPENID)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .redirectUri("http://client.localhost:8070/login/oauth2/code/myoauth2")
                .authorizationUri("http://auth.localhost:9000/oauth2/authorize")
                .tokenUri("http://auth.localhost:9000/oauth2/token")
                .jwkSetUri("http://auth.localhost:9000/oauth2/jwks")
                .build();
    }


}
