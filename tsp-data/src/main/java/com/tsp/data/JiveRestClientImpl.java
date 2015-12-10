package com.tsp.data;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestOperations;

@Slf4j
public class JiveRestClientImpl implements JiveRestClient {
	// TODO refactor this using spring property files
	private static final String jivePeople = "https://otpp-2.jiveon.com/api/core/v3/people/";
	// https://otpp-2.jiveon.com/api/core/v3/people/email/kevin_gu@otpp.com?fields=name
	// should we use this to do search?
	private static final String testRest1 = "https://otpp-2.jiveon.com/api/core/v3/people/52105/activities";
	// TODO implement placeholder for the people's id here

	private RestOperations sparklrRestTemplate;

	public void setSparklrRestTemplate(RestOperations sparklrRestTemplate) {
		this.sparklrRestTemplate = sparklrRestTemplate;
	}

	@Override
	public String getActivities() {
		String response = this.sparklrRestTemplate.getForObject(
				URI.create(testRest1), String.class);
		response = response.substring(response.indexOf("\n") + 1);
		log.debug("getActivities: " + response);
		return response;
	}

	@Override
	public String getPeople(long id) {
		String response = this.sparklrRestTemplate.getForObject(
				URI.create(jivePeople), String.class);
		response = response.substring(response.indexOf("\n") + 1);
		log.debug("getPeople: " + response);
		return response;
	}

}
