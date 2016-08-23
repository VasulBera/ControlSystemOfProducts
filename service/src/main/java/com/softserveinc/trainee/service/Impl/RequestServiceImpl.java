package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.dao.RequestTaskDao;
import com.softserveinc.trainee.entity.administration.RequestTask;
import com.softserveinc.trainee.entity.metadata.*;
import com.softserveinc.trainee.service.RequestService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
@JsonIgnoreProperties(ignoreUnknown=true)
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestTaskDao requestTaskDao;

    @Autowired
    EntityDao entityDao;

    @Autowired
    PreviousStateEntityDao previousStateEntityDao;

    @Override
    public void createTask(RequestTask requestTask) {
        requestTaskDao.createRequestTask(requestTask);
    }

    public void createTables(String id){
        Entity entity = entityDao.getEntity(id);
        if(entity == null){
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        requestTaskDao.createEntityTable(entity);
        previousStateEntityDao.addEntity(entity.createPreviousStateEntity());
    }

    @Override
    public void updateTable(String id) {
        Entity entity = entityDao.getEntity(id);
        if(entity == null){
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        PreviousStateEntity previousStateEntity = previousStateEntityDao.getEntity(id);
        requestTaskDao.updateTable(previousStateEntity, entity);
        previousStateEntityDao.updateEntity(entity.createPreviousStateEntity());
    }
}
