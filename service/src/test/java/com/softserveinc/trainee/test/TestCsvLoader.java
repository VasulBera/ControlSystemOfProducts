package com.softserveinc.trainee.test;

import com.softserveinc.trainee.applicationUtil.SalesForceConnection;
import com.softserveinc.trainee.dao.CsvFileDao;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.Impl.CsvFileDaoImpl;
import com.softserveinc.trainee.dao.Impl.CustomTableDaoImpl;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.executerImpl.CsvLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

public class TestCsvLoader {

    @Mock
    private static RequestJobDao requestJobDao;

    @Mock
    private static EntityDao entityDao;

    @Mock
    private static CustomTableDaoImpl customTableDaoImpl;

    @Mock
    private static CsvFileDaoImpl csvFileDao;

    @Mock
    private static SalesForceConnection salesForceConnection;

    @InjectMocks
    private static CsvLoader csvLoader = new CsvLoader();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testExecuteEntityNull(){
        RequestJob requestJob = new RequestJob();
        List<Entity> entities = new ArrayList();
        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        csvLoader.execute(requestJob);
    }

    @Test
    public void testExecuteWithEntitiesWithNotSchemaNameClient(){
        RequestJob requestJob = new RequestJob();
        List<Entity> entities = new ArrayList();
        Entity entity = new Entity();
        entity.setSchemaName("notclient");
        entities.add(entity);
        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        csvLoader.execute(requestJob);
    }

    @Test
    public void testExecuteWithEntitiesWithSchemaNameClientFullUploadIsFalse(){
        RequestJob requestJob = new RequestJob();
        List<Entity> entities = new ArrayList();
        Entity entity = new Entity();
        entity.setSchemaName("client");
        entity.setFullUploadData(false);
        entities.add(entity);
        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.doNothing().when(customTableDaoImpl).deleteAllRecord(entity);
        Mockito.doNothing().when(csvFileDao).uploadData(entity);
        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        csvLoader.execute(requestJob);
    }

    @Test
    public void testExecuteWithEntitiesWithSchemaNameClientFullUploadIsTrue(){
        RequestJob requestJob = new RequestJob();
        List<Entity> entities = new ArrayList();
        Entity entity = new Entity();
        entity.setSchemaName("client");
        entity.setFullUploadData(true);
        entities.add(entity);
        Mockito.doNothing().when(requestJobDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getAllEntity()).thenReturn(entities);
        Mockito.doNothing().when(customTableDaoImpl).deleteAllRecord(entity);
        Mockito.doNothing().when(csvFileDao).uploadData(entity);
        Mockito.when(requestJobDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(salesForceConnection).sendRequest(requestJob.getOwner());
        csvLoader.execute(requestJob);
    }
}
