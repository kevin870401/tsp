package com.tsp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.tsp.service.JiveRestService;
import com.tsp.LoginController;

@Configuration
public class ControllerConfig {
  
    @Autowired
    JiveRestService jiveRestService;
  
  
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
    
}