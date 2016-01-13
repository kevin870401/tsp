package com.tsp.data.entity.serializer;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.tsp.data.entity.JiveDirectMessage;
import com.tsp.data.entity.JivePeople;
import com.tsp.data.entity.JiveTextContent;

public class JiveDirectMessageDeserializer extends JsonDeserializer <JiveDirectMessage>{
	  private ObjectMapper mapper = new ObjectMapper();
	@Override
	public JiveDirectMessage deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		mapper.registerModule(new JodaModule());
		JiveDirectMessage result = new JiveDirectMessage();
		result.setId(node.get("id").asLong());
		result.setContentID(node.get("contentID").asLong());
		result.setSubject(node.get("subject").asText());
		result.setType(node.get("type").asText());
		result.setStatus(node.get("status").asText());
		result.setPublished(mapper.convertValue(node.get("published"), DateTime.class));
		result.setStartDate(mapper.convertValue(node.get("startDate"), DateTime.class));
		result.setEndDate(mapper.convertValue(node.get("endDate"), DateTime.class));
		JsonNode authorNode= node.get("author");
	    JivePeople author = mapper.convertValue(authorNode, JivePeople.class);
		result.setAuthor(author);
		
		JsonNode contentNode= node.get("content");
		JiveTextContent cotent = mapper.convertValue(contentNode, JiveTextContent.class);
		
		result.setContent(cotent);
		return result;
	}


}
