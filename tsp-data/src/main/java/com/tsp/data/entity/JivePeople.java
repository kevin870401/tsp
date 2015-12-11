package com.tsp.data.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JivePeople {
	private long id;
	private int followerCount;
	private String displayName;
	private List<JiveEmail> emails;
}
