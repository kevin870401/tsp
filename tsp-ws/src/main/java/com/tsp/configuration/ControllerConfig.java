package com.tsp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.tsp.service.JiveInboxService;
import com.tsp.service.JivePeopleService;
import com.tsp.LoginController;

@Configuration
public class ControllerConfig {
  
    @Autowired
    JivePeopleService jivePeopleService;
  
    @Autowired
    JiveInboxService jiveInboxService;
    
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
    
}