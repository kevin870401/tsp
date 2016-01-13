package com.tsp.data.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsp.data.entity.serializer.JiveDirectMessageDeserializer;
import com.tsp.data.entity.serializer.JiveDirectMessageSerializer;

import lombok.Data;

import org.joda.time.DateTime;

@Data
@JsonSerialize(using=JiveDirectMessageSerializer.class)
@JsonDeserialize(using=JiveDirectMessageDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveDirectMessage {
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
	private List<JivePeople> participants;
}
