package com.tsp.data.entity.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsp.data.entity.JiveDirectMessage;

public class JiveDirectMessageSerializer extends JsonSerializer <JiveDirectMessage>{

	@Override
	public void serialize(JiveDirectMessage value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		
	}




}
