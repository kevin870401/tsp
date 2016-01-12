package com.tsp.domain;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String type;// TODO enum or object
	private String status;// TODO enum or object
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private DateTime startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private DateTime endDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private DateTime published;
	
	private JiveTextContent content;
}
