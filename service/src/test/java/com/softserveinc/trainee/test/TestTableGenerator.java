package com.softserveinc.trainee.test;


import com.softserveinc.trainee.applicationUtil.SalesForceConnection;
import com.softserveinc.trainee.dao.CustomTableDao;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.executerImpl.TableGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class TestTableGenerator {

    @Mock
    private static RequestJobDao requestJobDao;

    @Mock
    private static EntityDao entityDao;

    @Mock
    private static PreviousStateEntityDao previousStateEntityDao;

    @Mock
    private static CustomTableDao customTableDao;

    @Mock
    private static SalesForceConnection salesForceConnection;

    @InjectMocks
    private static TableGenerator tableGenerator = new TableGenerator();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testExecuteNoEntities(){
        RequestJob requestJob = new RequestJob();
        List<Entity> entities = new ArrayList();
        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        tableGenerator.execute(requestJob);
    }

    @Test
    public void testExecuteUpdate(){
        RequestJob requestJob = new RequestJob();
        Entity entity = new Entity();
        entity.setId("OWNERPRODUCT");
        List<Entity> entities = new ArrayList();
        entities.add(entity);
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();

        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.when(previousStateEntityDao.addPreviousStateEntity(entity.createPreviousStateEntity())).thenReturn(false);
        Mockito.when(previousStateEntityDao.getPreviousStateEntity(entity.getId())).thenReturn(previousStateEntity);
        Mockito.doNothing().when(customTableDao).updateTable(previousStateEntity, entity);
        Mockito.when(previousStateEntityDao.updatePreviousStateEntity(entity.createPreviousStateEntity())).thenReturn(previousStateEntity);

        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        tableGenerator.execute(requestJob);
    }
}
