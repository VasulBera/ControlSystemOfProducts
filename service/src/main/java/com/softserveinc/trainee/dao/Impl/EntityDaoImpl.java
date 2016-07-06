package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("entityDao")
public class EntityDaoImpl implements EntityDao{

    @PersistenceContext
    private EntityManager entityManager;

    public Entity getEntity(String id) {
        return entityManager.find(Entity.class, id);
    }
}
