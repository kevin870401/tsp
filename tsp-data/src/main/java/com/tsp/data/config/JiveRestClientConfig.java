package com.tsp.data.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestOperations;

import com.tsp.data.JiveRestClient;
import com.tsp.data.JiveRestClientImpl;
import com.tsp.data.interceptor.JiveRestResponseInterceptor;


@Configuration
@PropertySource("classpath:config.properties")
@EnableOAuth2Client
public class JiveRestClientConfig {

  @Value("${oauth.accessToken.uri}")
  private String accessTokenUri;

  @Value("${oauth.userAuthorization.uri}")
  private String userAuthorizationUri;

  @Value("${oauth.client.id}")
  private String oauthClientId;

  @Value("${oauth.client.secret}")
  private String oauthClientSecret;

  @Value("${oauth.predefined.redirectUrl}")
  private String oauthPredefinedRedirectUrl;

  

  @Bean
  public OAuth2ProtectedResourceDetails jiveResourceDetails() {
      AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
      details.setId("sparklr/tonr");
      details.setClientId(oauthClientId);
      details.setClientSecret(oauthClientSecret);
      details.setAccessTokenUri(accessTokenUri);
      details.setUserAuthorizationUri(userAuthorizationUri);
      return details;
  }
  
  @Bean
  public OAuth2ProtectedResourceDetails jiveRedirectResourceDetails() {
    AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
    details.setId("sparklr/tonr-redirect");
    details.setClientId(oauthClientId);
    details.setClientSecret(oauthClientSecret);
    details.setAccessTokenUri(accessTokenUri);
    details.setUserAuthorizationUri(userAuthorizationUri);
    // not sure if scope needed to be set or not? or maybe for important resource this can
    // help oauth client avoid polluting data. => details.setScope(Arrays.asList("read", "write"));
    details.setUseCurrentUri(false);
    details.setPreEstablishedRedirectUri(oauthPredefinedRedirectUrl);
    details.setAuthenticationScheme(AuthenticationScheme.header);
    return details;
  }

  @Bean
  public OAuth2RestTemplate jiveRestTemplate(OAuth2ClientContext clientContext) {
    OAuth2RestTemplate sparklrRedirectRestTemplate = new OAuth2RestTemplate(jiveResourceDetails(), clientContext);
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(new JiveRestResponseInterceptor());
    sparklrRedirectRestTemplate.setInterceptors(interceptors);
    return sparklrRedirectRestTemplate;
  }
  
  @Bean
  public OAuth2RestTemplate jiveRedirectRestTemplate(OAuth2ClientContext clientContext) {
    OAuth2RestTemplate sparklrRedirectRestTemplate = new OAuth2RestTemplate(jiveRedirectResourceDetails(), clientContext);
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(new JiveRestResponseInterceptor());
    sparklrRedirectRestTemplate.setInterceptors(interceptors);
    return sparklrRedirectRestTemplate;
  }


  @Bean
  public JiveRestClient jiveRestClient(@Qualifier("jiveRestTemplate") RestOperations jiveRestTemplate) {
    JiveRestClientImpl jiveRestClient = new JiveRestClientImpl();
    jiveRestClient.setSparklrRestTemplate(jiveRestTemplate);
    return jiveRestClient;
  }
  
  @Bean
  public JiveRestClient jiveRestRedirectClient(@Qualifier("jiveRedirectRestTemplate") RestOperations jiveRedirectRestTemplate) {
    JiveRestClientImpl jiveRestClient = new JiveRestClientImpl();
    jiveRestClient.setSparklrRestTemplate(jiveRedirectRestTemplate);
    return jiveRestClient;
  }
  
}
