package com.tsp.domain;
import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.tsp.data.entity.JivePeople;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class JiveEvent {
	private long id;
	private long contentID;
	private String subject;
	private JivePeople author;
	private String type;//TODO enum or object
	private String status;//TODO enum or object
	//@JsonSerialize(using=DateTimeSerializer.class)
	private DateTime startDate;
	//@JsonSerialize(using=DateTimeSerializer.class)
	private DateTime endDate;
	//@JsonSerialize(using=DateTimeSerializer.class)
	private DateTime published;
}
