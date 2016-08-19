package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.RequestTaskDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestTaskDao requestTaskDao;

    @Autowired
    EntityDao entityDao;

    @Override
    public void createTask(String id, String description, String owner) {
        requestTaskDao.createRequestTask(id, description, owner);
    }

    public void createEntity(String id){
        Entity entity = entityDao.getEntity(id);
        if(entity != null){
            requestTaskDao.createEntityTable(entity);
        }else{
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
    }
}
