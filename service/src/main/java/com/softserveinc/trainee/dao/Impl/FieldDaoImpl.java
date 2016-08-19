package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.FieldDao;
import com.softserveinc.trainee.entity.metadata.Field;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("fieldDao")
public class FieldDaoImpl implements FieldDao{

    @PersistenceContext(unitName = "application")
    private EntityManager entityManager;

    @Override
    public Field getField(String id) {
        return entityManager.find(Field.class, id);
    }
}
