package com.tsp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class JivePeople {
	private long id;
	private int followerCount;
	private String displayName;
	private List<JiveEmail> emails;
}
