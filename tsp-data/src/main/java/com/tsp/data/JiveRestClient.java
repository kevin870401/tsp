package com.tsp.data;

import com.tsp.data.entity.JiveDirectMessage;
import com.tsp.data.entity.JiveEvent;
import com.tsp.data.entity.JivePeople;

public interface JiveRestClient {
  String getActivities();

  JivePeople getPeople(long id);

  String getInbox();

JiveEvent getEvent(long id);

  JiveDirectMessage getDirectMessage(long id);
}
