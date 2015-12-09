package com.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.tsp.interceptor.JiveRestResponseInterceptor;

@Configuration
public class ControllerConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    @Lazy
    public HttpEntity<String> jsonRequestEntity() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "application/json");
        return new HttpEntity<String>(requestHeaders);
    }
    
    @Bean
    public LoginController loginController(@Qualifier("jiveRestService") JiveRestService jiveRestService) {
      LoginController controller = new LoginController();
        controller.setJiveService(jiveRestService);
        return controller;
    }

    
    @Bean
    public JiveRestService jiveRestService( 
        @Value("${sparklrTrustedMessageURL}") String sparklrTrustedMessageURL,
        @Qualifier("sparklrRedirectRestTemplate") RestOperations sparklrRedirectRestTemplate,
        @Qualifier("trustedClientRestTemplate") RestOperations trustedClientRestTemplate) {
      JiveRestService sparklrService = new JiveRestService();
        sparklrService.setSparklrTrustedMessageURL(sparklrTrustedMessageURL);
        sparklrService.setSparklrRestTemplate(sparklrRedirectRestTemplate);
        sparklrService.setTrustedClientRestTemplate(trustedClientRestTemplate);
        return sparklrService;
    }


    @Configuration
    @EnableOAuth2Client
    protected static class ResourceConfiguration {

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
        public OAuth2ProtectedResourceDetails sparklr() {
            AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setId("sparklr/tonr");
            details.setClientId(oauthClientId);
            details.setClientSecret(oauthClientSecret);
            details.setAccessTokenUri(accessTokenUri);
            details.setUserAuthorizationUri(userAuthorizationUri);
            //details.setScope(Arrays.asList("read", "write"));
            return details;
        }

        @Bean
        public OAuth2ProtectedResourceDetails sparklrRedirect() {
            AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setId("sparklr/tonr-redirect");
            details.setClientId(oauthClientId);
            details.setClientSecret(oauthClientSecret);
            details.setAccessTokenUri(accessTokenUri);
            details.setUserAuthorizationUri(userAuthorizationUri);
            //details.setScope(Arrays.asList("read", "write"));
            details.setUseCurrentUri(false);
            details.setPreEstablishedRedirectUri(oauthPredefinedRedirectUrl);
            details.setAuthenticationScheme(AuthenticationScheme.header);
            return details;
        }

        @Bean
        public OAuth2ProtectedResourceDetails facebook() {
            AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setId("facebook");
            details.setClientId("233668646673605");
            details.setClientSecret("33b17e044ee6a4fa383f46ec6e28ea1d");
            details.setAccessTokenUri("https://graph.facebook.com/oauth/access_token");
            details.setUserAuthorizationUri("https://www.facebook.com/dialog/oauth");
            details.setTokenName("oauth_token");
            details.setAuthenticationScheme(AuthenticationScheme.query);
            details.setClientAuthenticationScheme(AuthenticationScheme.form);
            return details;
        }

        @Bean
        public OAuth2ProtectedResourceDetails trusted() {
            ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
            details.setId("sparklr/trusted");
            details.setClientId("my-client-with-registered-redirect");
            details.setAccessTokenUri(accessTokenUri);
            details.setScope(Arrays.asList("trust"));
            return details;
        }

        @Bean
        public OAuth2RestTemplate facebookRestTemplate(OAuth2ClientContext clientContext) {
            OAuth2RestTemplate template = new OAuth2RestTemplate(facebook(), clientContext);
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                    MediaType.valueOf("text/javascript")));
            template.setMessageConverters(Arrays.<HttpMessageConverter<?>> asList(converter));
            return template;
        }

        @Bean
        public OAuth2RestTemplate sparklrRestTemplate(OAuth2ClientContext clientContext) {
            return new OAuth2RestTemplate(sparklr(), clientContext);
        }

        @Bean
        public OAuth2RestTemplate sparklrRedirectRestTemplate(OAuth2ClientContext clientContext) {
        	OAuth2RestTemplate sparklrRedirectRestTemplate=new OAuth2RestTemplate(sparklrRedirect(), clientContext);
        	List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(); 
        	interceptors.add(new JiveRestResponseInterceptor());
        	sparklrRedirectRestTemplate.setInterceptors(interceptors);
            return sparklrRedirectRestTemplate;
        }

        @Bean
        public OAuth2RestTemplate trustedClientRestTemplate() {
            return new OAuth2RestTemplate(trusted(), new DefaultOAuth2ClientContext());
        }

    }
    
}