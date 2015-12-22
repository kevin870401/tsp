package com.tsp.domain;

import java.util.List;
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
