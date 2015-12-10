package com.tsp.service;

import java.io.IOException;
import java.net.URI;

import lombok.extern.slf4j.Slf4j;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.web.client.RestOperations;
@Slf4j
public class JiveRestService {
  private String jivePeople="https://otpp-2.jiveon.com/api/core/v3/people/email/kevin_gu@otpp.com?fields=name";
  private String testRest1="https://otpp-2.jiveon.com/api/core/v3/announcements?count=1&fields=publishDate,content,subject";
  private RestOperations sparklrRestTemplate;

  public JsonNode getPeople() {
	  String response = this.sparklrRestTemplate.getForObject(URI.create(testRest1), String.class);
	  response = response.substring(response.indexOf("\n") + 1);
	  log.debug("getPeople: "+response);
	  ObjectMapper mapper = new ObjectMapper();
	  JsonNode node = null;
	  try {
		  node= mapper.readTree(response);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  log.debug("getPeople: "+node.toString());
    return node;
  }

  public void setSparklrRestTemplate(RestOperations sparklrRestTemplate) {
      this.sparklrRestTemplate = sparklrRestTemplate;
  }

}
