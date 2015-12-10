package com.tsp.service.config;

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

import com.tsp.service.JiveRestService;
import com.tsp.service.interceptor.JiveRestResponseInterceptor;


@Configuration
@PropertySource("classpath:config.properties")
@EnableOAuth2Client
public class JiveRestServiceConfig {

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
  public OAuth2ProtectedResourceDetails sparklrRedirect() {
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
  public OAuth2RestTemplate sparklrRedirectRestTemplate(OAuth2ClientContext clientContext) {
    OAuth2RestTemplate sparklrRedirectRestTemplate = new OAuth2RestTemplate(sparklrRedirect(), clientContext);
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(new JiveRestResponseInterceptor());
    sparklrRedirectRestTemplate.setInterceptors(interceptors);
    return sparklrRedirectRestTemplate;
  }


  @Bean
  public JiveRestService jiveRestService(@Qualifier("sparklrRedirectRestTemplate") RestOperations sparklrRedirectRestTemplate) {
    JiveRestService jiveRestService = new JiveRestService();
    jiveRestService.setSparklrRestTemplate(sparklrRedirectRestTemplate);
    return jiveRestService;
  }
}
