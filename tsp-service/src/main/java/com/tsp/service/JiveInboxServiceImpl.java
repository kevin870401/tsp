package com.tsp.service;

import com.tsp.data.JiveRestClient;

public class JiveInboxServiceImpl implements JiveInboxService {
  private final JiveRestClient jiveRestClient;

  public JiveInboxServiceImpl(JiveRestClient jiveRestClient) {
    this.jiveRestClient = jiveRestClient;
  }
  @Override
  public String getInbox() {
    return jiveRestClient.getInbox();
  }

}
