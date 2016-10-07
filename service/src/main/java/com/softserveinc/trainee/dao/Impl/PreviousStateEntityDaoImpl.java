package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("previousStateEntityDao")
public class PreviousStateEntityDaoImpl implements PreviousStateEntityDao{

    @PersistenceContext(unitName = "administration")
    private EntityManager entityManager;

    @Override
    public PreviousStateEntity getPreviousStateEntity(String id) {
        return entityManager.find(PreviousStateEntity.class, id);
    }

    @Override
    public boolean addPreviousStateEntity(PreviousStateEntity previousStateEntity) {
        PreviousStateEntity previousStateEntityDb = entityManager.find(PreviousStateEntity.class, previousStateEntity.getId());
        if(previousStateEntityDb != null){
            return true;
        }
        entityManager.persist(previousStateEntity);
        return false;
    }

    @Override
    public PreviousStateEntity updatePreviousStateEntity(PreviousStateEntity previousStateEntity) {
        PreviousStateEntity previousStateEntityPersist = entityManager.find(PreviousStateEntity.class, previousStateEntity.getId());
        if(previousStateEntityPersist != null){
            previousStateEntity.setCreatedDate(previousStateEntityPersist.getCreatedDate());
            previousStateEntity.addLastModifierDate(previousStateEntityPersist);
            fieldLable: for(PreviousStateField previousStateFieldPesisted: previousStateEntityPersist.getFieldList()) {
                for (PreviousStateField previousStateField : previousStateEntity.getFieldList()) {
                    if (previousStateFieldPesisted.getId().equals(previousStateField.getId())) {
                        previousStateField.setCreatedDate(previousStateFieldPesisted.getCreatedDate());
                        previousStateField.addLastModifierDate(previousStateFieldPesisted);
                        continue fieldLable;
                    }
                }
                entityManager.remove(previousStateFieldPesisted);
            }
        }
        return entityManager.merge(previousStateEntity);
    }
}
