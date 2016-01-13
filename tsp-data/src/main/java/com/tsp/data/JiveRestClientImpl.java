package com.tsp.data;

import java.io.IOException;
import java.net.URI;

import com.tsp.data.entity.JiveDirectMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsp.data.entity.JiveEvent;
import com.tsp.data.entity.JivePeople;

//@Slf4j
public class JiveRestClientImpl implements JiveRestClient {
  // TODO refactor this using spring property files
  private static final String jivePeople = "/people/";
  private static final String jiveContent = "/contents/";
  private static final String jiveDms = "/dms/";
  private static final String inboxUrl = "/inbox";
  // https://otpp-2.jiveon.com/api/core/v3/people/email/kevin_gu@otpp.com?fields=name
  // should we use this to do search?
  private static final String testRest1 = "https://otpp-2.jiveon.com/api/core/v3/people/52105/activities";
  // TODO implement placeholder for the people's id here

  private RestOperations sparklrRestTemplate;

  private ObjectMapper mapper = new ObjectMapper();
  
  private String jiveResourceBaseUrl;
  

  public JiveRestClientImpl(String sparkResouceBaseUrl) {
	  jiveResourceBaseUrl=sparkResouceBaseUrl;
}

public void setSparklrRestTemplate(RestOperations sparklrRestTemplate) {
    this.sparklrRestTemplate = sparklrRestTemplate;
  }

  @Override
  public String getActivities() {
    String response = this.sparklrRestTemplate.getForObject(URI.create(testRest1), String.class);
    response = response.substring(response.indexOf("\n") + 1);
    //log.debug("getActivities: " + response);
    return response;
  }
  
  @Override
  public JiveEvent getEvent(long id) {
	    String response = getResponse(jiveResourceBaseUrl.concat(jiveContent.concat(String.valueOf(id))));
	    JiveEvent event = null;
	    try {
	    	event = mapper.readValue(response, JiveEvent.class);
	    } catch (JsonParseException e) {// TODO don't eat these exceptions
	      //log.error(e.getMessage());
	    } catch (JsonMappingException e) {
	      //log.error(e.getMessage());
	    } catch (IOException e) {
	      //log.error(e.getMessage());
	    }
	    return event;
  }

  @Override
  public JiveDirectMessage getDirectMessage(long id) {
    String response = getResponse(jiveResourceBaseUrl.concat(jiveDms.concat(String.valueOf(id))));
    JiveDirectMessage jiveDirectMessage = null;
    try {
      jiveDirectMessage = mapper.readValue(response, JiveDirectMessage.class);
    } catch (JsonParseException e) {// TODO don't eat these exceptions
      //log.error(e.getMessage());
    } catch (JsonMappingException e) {
      //log.error(e.getMessage());
    } catch (IOException e) {
      //log.error(e.getMessage());
    }
    return jiveDirectMessage;
  }

  @Override
  public JivePeople getPeople(long id) {
    String response = getResponse(jiveResourceBaseUrl.concat(jivePeople.concat(String.valueOf(id))));
    JivePeople people = null;
    try {
      people = mapper.readValue(response, JivePeople.class);
    } catch (JsonParseException e) {// TODO don't eat these exceptions
      //log.error(e.getMessage());
    } catch (JsonMappingException e) {
      //log.error(e.getMessage());
    } catch (IOException e) {
      //log.error(e.getMessage());
    }
    return people;
  }

  @Override
  public String getInbox() {
    String response = getResponse(jiveResourceBaseUrl.concat(inboxUrl));
    return response;
  }

  // due to Jive's GET response has an extra line. this method retrieve the correct response from
  // jive
  private String getResponse(String uri) {
    String response = this.sparklrRestTemplate.getForObject(URI.create(uri), String.class);
    response = response.substring(response.indexOf("\n") + 1);
    //log.debug("getResponse: " + response);
    return response;
  }

}
