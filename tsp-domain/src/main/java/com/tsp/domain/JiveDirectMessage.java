package com.tsp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tsp.data.entity.JivePeople;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

@Data
@ToString(includeFieldNames = true)
public class JiveDirectMessage {
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
