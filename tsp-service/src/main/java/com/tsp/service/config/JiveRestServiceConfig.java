package com.tsp.service.config;

import com.tsp.service.*;
import com.tsp.service.mappers.JiveDirectMessageMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tsp.data.JiveRestClient;
import com.tsp.service.mappers.JiveEventMapper;
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
  public JiveEventService jiveEventService() {
    JiveEventServiceImpl jiveEventService = new JiveEventServiceImpl(jiveRestRedirectClient, jiveEventMapper());
    return jiveEventService;
  }

  @Bean
  public JiveDirectMessageService jiveDirectMessageService() {
    JiveDirectMessageServiceImpl jiveDirectMessageService = new JiveDirectMessageServiceImpl(jiveRestRedirectClient, jiveDirectMessageMapper());
    return jiveDirectMessageService;
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
  public JiveEventMapper jiveEventMapper() {
    return new JiveEventMapper(defaultMapperFactory());
  }

  @Bean
  public JiveDirectMessageMapper jiveDirectMessageMapper() {
    return new JiveDirectMessageMapper(defaultMapperFactory());
  }

  @Bean
  public MapperFactory defaultMapperFactory() {
    return new DefaultMapperFactory.Builder().build();
  }

}
