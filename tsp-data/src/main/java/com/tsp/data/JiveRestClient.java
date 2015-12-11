package com.tsp.data;

import com.tsp.data.entity.JivePeople;

public interface JiveRestClient {
  String getActivities();

  JivePeople getPeople(long id);

  String getInbox();
}
