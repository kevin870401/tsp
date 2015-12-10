package com.tsp.service;

import java.io.IOException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsp.data.JiveRestClient;
import com.tsp.domain.JiveActivity;
import com.tsp.domain.JivePeople;

@Slf4j
public class JiveRestServiceImpl implements JiveRestService {
	
	private JiveRestClient jiveRestClient;

	public void setJiveRestClient(JiveRestClient jiveRestClient){
		this.jiveRestClient= jiveRestClient;
	}

	@Override
	public List<JiveActivity> getActivites() {
		String jsonString = jiveRestClient.getActivities();
		ObjectMapper mapper = new ObjectMapper();
		List<JiveActivity> activities = null;
		try {
			activities = mapper.readValue(jsonString,
					new TypeReference<List<JiveActivity>>() {
					});
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
		String jsonString = jiveRestClient.getPeople(id);
		ObjectMapper mapper = new ObjectMapper();
		JivePeople people= null;
		try {
			people = mapper.readValue(jsonString,JivePeople.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return people;
	}
	
}
