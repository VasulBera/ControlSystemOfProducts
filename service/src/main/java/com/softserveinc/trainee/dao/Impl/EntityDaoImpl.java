package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("entityDao")
public class EntityDaoImpl implements EntityDao{

    @PersistenceContext(unitName = "application")
    private EntityManager entityManager;

    public Entity getEntity(String id) {
        System.out.println("in entoity dao imp");
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
            entityManager.remove(persistEntity);
        }
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void deleteEntity(Entity entity) {
            entityManager.remove(entity);
    }

    @Override
    public List<Entity> getAllEntity() {
        Query query = entityManager.createQuery("SELECT e FROM Entity e");
        return query.getResultList();
    }
}
