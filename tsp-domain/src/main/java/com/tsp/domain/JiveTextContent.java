package com.tsp.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class JiveTextContent {
	private String text;
	private boolean editable;
	private String type;
}
