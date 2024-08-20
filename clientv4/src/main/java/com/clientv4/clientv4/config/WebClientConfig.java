package com.clientv4.clientv4.config;

import com.clientv4.clientv4.Controller.WelcomeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Bean
     public WelcomeClient welcomeClient(OAuth2AuthorizedClientManager authorizedClientManager) {

        //This is creating an instance of Welcome client that is configured to use OAuth2 for each HTTP request
         return httpServiceProxyFactory(authorizedClientManager).createClient(WelcomeClient.class);

     }

     private HttpServiceProxyFactory httpServiceProxyFactory(OAuth2AuthorizedClientManager authorizedClientManager){

        //This creates a function which applies OAuth2 authorization to each request made by this webclient
         ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

         //Configures the filter to automatically use the default OAuth2 authorized client for all requests
         oauth2Client.setDefaultOAuth2AuthorizedClient(true);

         //Builds a WebClient instance that is configured to use the OAuth2 authorization filter
         WebClient webClient = WebClient.builder().apply(oauth2Client.oauth2Configuration())
                 .build();

         // Adapts the WebClient to be used in an HttpServiceProxyFactory
         WebClientAdapter client = WebClientAdapter.forClient(webClient);

         // Creates and returns an HttpServiceProxyFactory that can generate clients like WelcomeClient
         return HttpServiceProxyFactory. builder(client).build();
     }

     @Bean
     public OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository){

         OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                 .authorizationCode()
                 .refreshToken()
                 .build();


         DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
         authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
         return authorizedClientManager;
     }
}
