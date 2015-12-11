package com.tsp.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveEmail {
	private boolean primary;
	private String type;
	private String value;
	@JsonProperty("jive_displayOrder")
	private int displayOrder;
}
