package com.tsp.data.entity.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tsp.data.entity.JiveEvent;

public class JiveEventSerializer extends JsonSerializer <JiveEvent>{

	@Override
	public void serialize(JiveEvent value, JsonGenerator gen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		
	}




}
