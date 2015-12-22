package com.tsp.service.mappers;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.metadata.TypeFactory;
public class JiveEventMapper extends DefaultMapper<com.tsp.domain.JiveEvent, com.tsp.data.entity.JiveEvent> {

  public JiveEventMapper(MapperFactory mapperFactory) {
    super(mapperFactory);
  }
  
  @PostConstruct
  public void init() {
      configureMapperFactory();
  }

  @Override
  protected void configureMapperFactory() {
	  getDefaultMapperFactory().registerObjectFactory(new ObjectFactory<DateTime>() { 
		  @Override 
		  public DateTime create(Object source, MappingContext mappingContext) { 
		    return (DateTime) source; 
		  } 
		}, TypeFactory.valueOf(DateTime.class)); 
	  
      getDefaultMapperFactory().classMap(entityClass(), domainClass())
                               .byDefault()
                               .customize(new CustomMapper<com.tsp.data.entity.JiveEvent, com.tsp.domain.JiveEvent>() {

                            	   @Override
                                   public void mapAtoB( com.tsp.data.entity.JiveEvent entityJiveEvent,com.tsp.domain.JiveEvent domainJiveEvent,
                                           MappingContext context) {
                            		   domainJiveEvent.setStartDate(entityJiveEvent.getStartDate());
                            		   domainJiveEvent.setEndDate(entityJiveEvent.getEndDate());
                            		   domainJiveEvent.setPublished(entityJiveEvent.getPublished());
                                   }
                            	   
                                   @Override
                                   public void mapBtoA(com.tsp.domain.JiveEvent domainJiveEvent, com.tsp.data.entity.JiveEvent entityJiveEvent,
                                           MappingContext context) {
                                	   entityJiveEvent.setStartDate(domainJiveEvent.getStartDate());
                                	   entityJiveEvent.setEndDate(domainJiveEvent.getEndDate());
                                	   entityJiveEvent.setPublished(domainJiveEvent.getPublished());
                                   }
                               })
                               .register();
  }
  @Override
  protected Class<com.tsp.data.entity.JiveEvent> entityClass() {
    return com.tsp.data.entity.JiveEvent.class;
  }

  @Override
  protected Class<com.tsp.domain.JiveEvent> domainClass() {
    return com.tsp.domain.JiveEvent.class;
  }

}
