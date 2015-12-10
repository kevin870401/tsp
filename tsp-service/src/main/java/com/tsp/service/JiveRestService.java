package com.tsp.service;

import java.util.List;



import com.tsp.domain.JiveActivity;
import com.tsp.domain.JivePeople;

public interface JiveRestService {
	  public List<JiveActivity> getActivites();
	  public JivePeople getPeople(long id);
}
