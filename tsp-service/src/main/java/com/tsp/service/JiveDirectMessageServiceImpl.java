package com.tsp.service;

import com.tsp.data.JiveRestClient;
import com.tsp.domain.JiveDirectMessage;
import com.tsp.service.mappers.JiveDirectMessageMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JiveDirectMessageServiceImpl implements JiveDirectMessageService {

  private final JiveRestClient jiveRestClient;

  private final JiveDirectMessageMapper jiveDirectMessageMapper;

  public JiveDirectMessageServiceImpl(JiveRestClient jiveRestClient, JiveDirectMessageMapper jiveDirectMessageMapper) {
    this.jiveRestClient = jiveRestClient;
    this.jiveDirectMessageMapper = jiveDirectMessageMapper;
  }


  @Override
  public JiveDirectMessage getDirectMessage(long id) {
    return jiveDirectMessageMapper.mapEntityToDomain(jiveRestClient.getDirectMessage(id));
  }

}
