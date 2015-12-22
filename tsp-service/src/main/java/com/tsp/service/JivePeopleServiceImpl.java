package com.tsp.service;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsp.data.JiveRestClient;
import com.tsp.domain.JiveActivity;
import com.tsp.domain.JivePeople;
import com.tsp.service.mappers.JivePeopleMapper;

@Slf4j
public class JivePeopleServiceImpl implements JivePeopleService {

  private final JiveRestClient jiveRestClient;

  private final JivePeopleMapper jivePeopleMapper;

  public JivePeopleServiceImpl(JiveRestClient jiveRestClient, JivePeopleMapper jivePeopleMapper) {
    this.jiveRestClient = jiveRestClient;
    this.jivePeopleMapper = jivePeopleMapper;
  }

  @Override
  public List<JiveActivity> getActivites() {
    String jsonString = jiveRestClient.getActivities();
    ObjectMapper mapper = new ObjectMapper();
    List<JiveActivity> activities = null;
    try {
      activities = mapper.readValue(jsonString, new TypeReference<List<JiveActivity>>() {});
    } catch (JsonParseException e) {
      log.error(e.getMessage());
    } catch (JsonMappingException e) {
      log.error(e.getMessage());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return activities;
  }

  @Override
  public JivePeople getPeople(long id) {
    return jivePeopleMapper.mapEntityToDomain(jiveRestClient.getPeople(id));
  }

}
