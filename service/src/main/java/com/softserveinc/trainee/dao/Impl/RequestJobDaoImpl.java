package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("requestTaskDao")
public class RequestJobDaoImpl implements RequestJobDao {

    @PersistenceContext(unitName = "administration")
    private EntityManager entityManager;

    @Override
    public void createRequestTask(RequestJob requestJob) {
        entityManager.persist(requestJob);
    }

    @Override
    public RequestJob updateRequestTask(RequestJob requestJob) {
        RequestJob requestTaskDb = entityManager.merge(requestJob);
        return requestTaskDb;
    }
}
