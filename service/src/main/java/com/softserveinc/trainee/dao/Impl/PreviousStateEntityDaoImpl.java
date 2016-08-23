package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.entity.metadata.PreviousStateEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("previousStateEntityDao")
public class PreviousStateEntityDaoImpl implements PreviousStateEntityDao{

    @PersistenceContext(unitName = "administration")
    private EntityManager entityManager;

    @Override
    public PreviousStateEntity getEntity(String id) {
        return entityManager.find(PreviousStateEntity.class, id);
    }

    @Override
    public void addEntity(PreviousStateEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public PreviousStateEntity updateEntity(PreviousStateEntity entity) {
        entityManager.merge(entity);
        return entity;
    }
}
