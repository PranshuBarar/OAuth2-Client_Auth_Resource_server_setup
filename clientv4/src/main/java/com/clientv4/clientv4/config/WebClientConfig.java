package com.clientv4.clientv4.config;

import com.clientv4.clientv4.Controller.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    //Now this below function will provide the actual implementation of the Client declared as an interface
    @Bean
     public Client welcomeClient(OAuth2AuthorizedClientManager authorizedClientManager) {

        //this is creating an instance of Welcome client that is configured to use OAuth2 for each HTTP request
        //this will send request to Resource Server with access token
         return httpServiceProxyFactory(authorizedClientManager).createClient(Client.class);

     }

     private HttpServiceProxyFactory httpServiceProxyFactory(OAuth2AuthorizedClientManager authorizedClientManager){

         //This function is actually responsible for applying oauth2 authorization (like basically attaching access token to the request)
         //on each request which WebClient sends to resource server
         ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

         //Configures the filter to automatically use the default OAuth2 authorized client for all requests
         oauth2Client.setDefaultOAuth2AuthorizedClient(true);

         //Builds a WebClient instance that is configured to use the OAuth2 authorization filter
         WebClient webClient = WebClient.builder().apply(oauth2Client.oauth2Configuration())
                 .build();

         // Adapts the WebClient to be used in an HttpServiceProxyFactory
         WebClientAdapter client = WebClientAdapter.forClient(webClient);

         // Creates and returns an HttpServiceProxyFactory that can generate clients like Client
         return HttpServiceProxyFactory.builder(client).build();
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
