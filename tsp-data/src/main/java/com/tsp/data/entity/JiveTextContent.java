package com.tsp.data.entity;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveTextContent {
	private String text;
	private boolean editable;
	private String type;
}
