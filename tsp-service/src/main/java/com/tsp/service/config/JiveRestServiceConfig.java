package com.tsp.service.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsp.data.JiveRestClient;
import com.tsp.service.JiveInboxService;
import com.tsp.service.JiveInboxServiceImpl;
import com.tsp.service.JivePeopleService;
import com.tsp.service.JivePeopleServiceImpl;
import com.tsp.service.mappers.JivePeopleMapper;

@Configuration
public class JiveRestServiceConfig {

  @Autowired
  @Qualifier("jiveRestClient")
  JiveRestClient jiveRestClient;

  @Autowired
  @Qualifier("jiveRestRedirectClient")
  JiveRestClient jiveRestRedirectClient;

  // TODO discuss if we want to directly expose this low level external
  // service as a bean which can be accessed by class in controller level

  @Bean
  public JivePeopleService jivePeopleService() {
    JivePeopleServiceImpl jivePeopleService = new JivePeopleServiceImpl(jiveRestRedirectClient, jivePeopleMapper());
    return jivePeopleService;
  }

  @Bean
  public JiveInboxService jiveInboxService() {
    JiveInboxServiceImpl jiveDirectRestService = new JiveInboxServiceImpl(jiveRestClient);
    return jiveDirectRestService;
  }
  
  @Bean
  public JivePeopleMapper jivePeopleMapper() {
    return new JivePeopleMapper(defaultMapperFactory());
  }

  @Bean
  public MapperFactory defaultMapperFactory() {
    return new DefaultMapperFactory.Builder().build();
  }

}
