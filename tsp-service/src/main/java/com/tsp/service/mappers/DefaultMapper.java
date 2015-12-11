package com.tsp.service.mappers;


import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class DefaultMapper<DomainClass, DatabaseClass> {

    private final MapperFactory defaultMapperFactory;

    @Autowired
    public DefaultMapper(MapperFactory mapperFactory) {
        this.defaultMapperFactory = mapperFactory;
    }

    public List<DomainClass> mapEntityToDomain(List<DatabaseClass> dbObjects) {
        return (List<DomainClass>) getMapperFacade().mapAsList(dbObjects, domainClass());
    }

    public DomainClass mapEntityToDomain(DatabaseClass dbObject) {
        return (DomainClass) getMapperFacade().map(dbObject, domainClass());
    }

    public List<DatabaseClass> mapDomainToEntity(List<DomainClass> domainObjects) {
        return (List<DatabaseClass>) getMapperFacade().mapAsList(domainObjects, entityClass());
    }

    public DatabaseClass mapDomainToEntity(DomainClass domainObject) {
        return (DatabaseClass) getMapperFacade().map(domainObject, entityClass());
    }

    /*
     * Subclasses that need to change the mapper factory configuration can override this method.
     * Common use cases for overriding are when most of the fields are copied by default but there are some exceptions,
     * mappers can still use the default mapper but need to specify additionally the mappings for the exceptional fields.
     */
    protected void configureMapperFactory() {
        getDefaultMapperFactory().classMap(entityClass(), domainClass()).byDefault().register();
    }

    protected MapperFactory getDefaultMapperFactory() {
        return defaultMapperFactory;
    }

    protected abstract Class<DatabaseClass> entityClass();

    protected abstract Class<DomainClass> domainClass();

    private MapperFacade getMapperFacade() {
        return getDefaultMapperFactory().getMapperFacade();
    }

}