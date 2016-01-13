package com.tsp.service.mappers;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;
import ma.glasnost.orika.metadata.TypeFactory;
import org.joda.time.DateTime;

import javax.annotation.PostConstruct;

public class JiveDirectMessageMapper extends DefaultMapper<com.tsp.domain.JiveDirectMessage, com.tsp.data.entity.JiveDirectMessage> {

  public JiveDirectMessageMapper(MapperFactory mapperFactory) {
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
                               .customize(new CustomMapper<com.tsp.data.entity.JiveDirectMessage, com.tsp.domain.JiveDirectMessage>() {

                            	   @Override
                                   public void mapAtoB( com.tsp.data.entity.JiveDirectMessage entityJiveDirectMessage,com.tsp.domain.JiveDirectMessage domainJiveDirectMessage,
                                           MappingContext context) {
                            		   domainJiveDirectMessage.setStartDate(entityJiveDirectMessage.getStartDate());
                            		   domainJiveDirectMessage.setEndDate(entityJiveDirectMessage.getEndDate());
                            		   domainJiveDirectMessage.setPublished(entityJiveDirectMessage.getPublished());
                                   }
                            	   
                                   @Override
                                   public void mapBtoA(com.tsp.domain.JiveDirectMessage domainJiveEvent, com.tsp.data.entity.JiveDirectMessage entityJiveDirectMessage,
                                           MappingContext context) {
                                	   entityJiveDirectMessage.setStartDate(domainJiveEvent.getStartDate());
                                	   entityJiveDirectMessage.setEndDate(domainJiveEvent.getEndDate());
                                	   entityJiveDirectMessage.setPublished(domainJiveEvent.getPublished());
                                   }
                               })
                               .register();
  }
  @Override
  protected Class<com.tsp.data.entity.JiveDirectMessage> entityClass() {
    return com.tsp.data.entity.JiveDirectMessage.class;
  }

  @Override
  protected Class<com.tsp.domain.JiveDirectMessage> domainClass() {
    return com.tsp.domain.JiveDirectMessage.class;
  }

}
