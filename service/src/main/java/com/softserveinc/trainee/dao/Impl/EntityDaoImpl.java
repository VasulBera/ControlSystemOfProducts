package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Repository("entityDao")
public class EntityDaoImpl implements EntityDao {

    @PersistenceContext(unitName = "application")
    private EntityManager entityManager;

    public Entity getEntity(String id) {
        return entityManager.find(Entity.class, id);
    }

    @Override
    public Entity addEntity(Entity entity) {
        entityManager.persist(entity);
        return entityManager.find(Entity.class, entity.getId());
    }

    @Override
    public Entity updateEntity(Entity entity) {
        Entity persistEntity = entityManager.find(Entity.class, entity.getId());
        if(persistEntity != null){
            entity.setCreatedDate(persistEntity.getCreatedDate());
            for(Field fieldPersisted: persistEntity.getFieldList()){
                for(Field field: entity.getFieldList()){
                    if(fieldPersisted.getId().equals(field.getId())){
                        field.setCreatedDate(fieldPersisted.getCreatedDate());
                    }
                }
            }
            entityManager.merge(entity);
            return entity;
        }
        throw new ClientErrorException(Response.Status.NOT_FOUND);
    }

    @Override
    public void deleteEntity(String id) {
        Entity entity = entityManager.find(Entity.class, id);
        if(entity != null){
            entityManager.remove(entity);
        }else{
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
    }

    @Override
    public List<Entity> getAllEntity() {
        Query query = entityManager.createQuery("SELECT e FROM Entity e");
        return query.getResultList();
    }
}
