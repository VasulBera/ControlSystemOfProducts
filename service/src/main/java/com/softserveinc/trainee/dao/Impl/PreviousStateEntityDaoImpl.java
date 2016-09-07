package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

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
            return false;
        }
        entityManager.persist(previousStateEntity);
        return true;
    }

    @Override
    public PreviousStateEntity updatePreviousStateEntity(PreviousStateEntity previousStateEntity) {
        PreviousStateEntity previousStateEntityPersist = entityManager.find(PreviousStateEntity.class, previousStateEntity.getId());
        if(previousStateEntityPersist != null){
            for(PreviousStateField previousStateFieldPesisted: previousStateEntityPersist.getFieldList()){
                for(PreviousStateField previousStateField: previousStateEntity.getFieldList()){
                    if(previousStateFieldPesisted.getId().equals(previousStateField.getId())){
                        previousStateField.setCreatedDate(previousStateFieldPesisted.getCreatedDate());
                    }
                }
            }
            previousStateEntity.setCreatedDate(previousStateEntityPersist.getCreatedDate());
            return entityManager.merge(previousStateEntity);
        }
        throw new ClientErrorException(Response.Status.NOT_FOUND);
    }
}
