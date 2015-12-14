package com.tsp.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import com.tsp.service.JiveInboxService;
import com.tsp.service.JivePeopleService;
import com.tsp.CurrentUserHandlerMethodArgumentResolver;
import com.tsp.LandingController;
import com.tsp.LoginController;
import com.tsp.SAMLUserDetailsServiceImpl;
import com.tsp.SsoController;

@Configuration
public class ControllerConfig extends WebMvcConfigurerAdapter {
  
    @Autowired
    JivePeopleService jivePeopleService;
  
    @Autowired
    JiveInboxService jiveInboxService;
    @Autowired
    CurrentUserHandlerMethodArgumentResolver currentUserHandlerMethodArgumentResolver;
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/error").setViewName("error");
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(currentUserHandlerMethodArgumentResolver);
    }
    
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
    public LoginController loginController() {
      LoginController controller = new LoginController(jivePeopleService,jiveInboxService);
        return controller;
    }
 
    @Bean
    public SsoController ssoController() {
      SsoController ssoController = new SsoController();
        return ssoController;
    }
    
    @Bean
    public LandingController landingController() {
      LandingController landingController = new LandingController();
        return landingController;
    }
    

    
}