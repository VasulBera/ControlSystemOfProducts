package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.applicationConfig.FieldComparator;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.service.Impl.EntityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Repository("entityDao")
public class EntityDaoImpl implements EntityDao {

    @PersistenceContext(unitName = "application")
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityDaoImpl.class);

    public Entity getEntity(String id) {
        Entity entity = entityManager.find(Entity.class, id);
        if(entity == null){
            LOGGER.warn("Method: getEntity(String id); No entity with id = " + id);
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        if(!entity.getFieldList().isEmpty()){
            Collections.sort(entity.getFieldList(), new FieldComparator());
        }
        return entity;
    }

    @Override
    public Entity addEntity(Entity entity) {
        Entity persistedEntity = entityManager.find(Entity.class, entity.getId());
        if(persistedEntity != null){
            LOGGER.warn("Method: addEntity(Entity entity); entity with name = " + entity.getName() + " is exist");
            new ClientErrorException(Response.Status.BAD_REQUEST);
        }
        entityManager.persist(entity);
        return entityManager.find(Entity.class, entity.getId());
    }

    @Override
    public Entity updateEntity(Entity entity) {
        Entity persistedEntity = entityManager.find(Entity.class, entity.getId());
        if(persistedEntity != null){
            entity.setCreatedDate(persistedEntity.getCreatedDate());
            entity.addLastModifierDate(persistedEntity);
            fieldLable: for(Field persistedField: persistedEntity.getFieldList()){
               for(Field field: entity.getFieldList()){
                   if(field.getId().equals(persistedField.getId())){
                       field.setCreatedDate(persistedField.getCreatedDate());
                       field.addLastModifierDate(persistedField);
                       continue fieldLable;
                   }
               }
                entityManager.remove(persistedField);
            }
        }
        return entityManager.merge(entity);
    }

    @Override
    public void deleteEntity(String id) {
        Entity entity = entityManager.find(Entity.class, id);
        if(entity != null){
            entityManager.remove(entity);
        }else{
            LOGGER.warn("Method: deleteEntity(String id); No entity with id = " + id + "for deleting");
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
    }

    @Override
    public List<Entity> getAllEntities() {
        Query query = entityManager.createQuery("SELECT e FROM Entity e");
        List<Entity> entities = query.getResultList();
        if(entities.isEmpty()) {
            LOGGER.warn("Method: getAllEntities(); No entities");
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        for(Entity entity: entities){
            if(!entity.getFieldList().isEmpty()){
                Collections.sort(entity.getFieldList(), new FieldComparator());
            }
        }
        return entities;
    }

    @Override
    public List<Entity> getEntitiesWithShemaNameClient() {
        Query query = entityManager.createQuery("SELECT e FROM Entity e WHERE schema_name = 'client'");
        List<Entity> entities = query.getResultList();
        for(Entity entity: entities){
            if(!entity.getFieldList().isEmpty()){
                Collections.sort(entity.getFieldList(), new FieldComparator());
            }
        }
        return entities;
    }
}
