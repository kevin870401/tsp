package com.tsp.data.entity;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsp.data.entity.serializer.JiveEventDeserializer;
import com.tsp.data.entity.serializer.JiveEventSerializer;

import lombok.Data;

@Data
@JsonSerialize(using=JiveEventSerializer.class)
@JsonDeserialize(using=JiveEventDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveEvent {
	private long id;
	private long contentID;
	private String subject;
	private JivePeople author;
	private String type;//TODO enum or object
	private String status;//TODO enum or object
	private DateTime startDate;
	private DateTime endDate;
	private DateTime published;
	private JiveTextContent content;
}
