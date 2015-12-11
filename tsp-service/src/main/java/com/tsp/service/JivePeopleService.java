package com.tsp.service;

import java.util.List;



import com.tsp.domain.JiveActivity;
import com.tsp.domain.JivePeople;

public interface JivePeopleService {
	  List<JiveActivity> getActivites();
	  JivePeople getPeople(long id);
}
