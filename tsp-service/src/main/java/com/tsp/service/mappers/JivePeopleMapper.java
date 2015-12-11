package com.tsp.service.mappers;

import com.tsp.data.entity.JivePeople;

import ma.glasnost.orika.MapperFactory;

public class JivePeopleMapper extends DefaultMapper<com.tsp.domain.JivePeople, com.tsp.data.entity.JivePeople> {

  public JivePeopleMapper(MapperFactory mapperFactory) {
    super(mapperFactory);
  }

  @Override
  protected Class<com.tsp.data.entity.JivePeople> entityClass() {
    return com.tsp.data.entity.JivePeople.class;
  }

  @Override
  protected Class<com.tsp.domain.JivePeople> domainClass() {
    return com.tsp.domain.JivePeople.class;
  }

}
