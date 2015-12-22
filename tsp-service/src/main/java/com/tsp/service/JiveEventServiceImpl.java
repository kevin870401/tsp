package com.tsp.service;

import lombok.extern.slf4j.Slf4j;

import com.tsp.data.JiveRestClient;
import com.tsp.domain.JiveEvent;
import com.tsp.service.mappers.JiveEventMapper;

@Slf4j
public class JiveEventServiceImpl implements JiveEventService {

  private final JiveRestClient jiveRestClient;

  private final JiveEventMapper jiveEventMapper;

  public JiveEventServiceImpl(JiveRestClient jiveRestClient, JiveEventMapper jiveEventMapper) {
    this.jiveRestClient = jiveRestClient;
    this.jiveEventMapper = jiveEventMapper;
  }


  @Override
  public JiveEvent getEvent(long id) {
    return jiveEventMapper.mapEntityToDomain(jiveRestClient.getEvent(id));
  }

}
