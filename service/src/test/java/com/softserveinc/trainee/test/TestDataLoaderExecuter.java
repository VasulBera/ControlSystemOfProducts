package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.Impl.DataLoaderExecutor;
import com.softserveinc.trainee.loader.DataLoader;
import com.softserveinc.trainee.sender.RequestSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestDataLoaderExecuter {

    @Mock
    RequestJobDao requestTaskDao;

    @Mock
    EntityDao entityDao;

    @Mock
    RequestSender requestSender;

    @Mock
    DataLoader dataLoader;

    @InjectMocks
    DataLoaderExecutor dataLoaderExecutor = new DataLoaderExecutor();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteNoEntities(){
        RequestJob requestJob = new RequestJob();
        requestJob.setAim("Apply_metadata");
        requestJob.setStatus(RequestJobStatus.ACTUAL);
        requestJob.setOwner("VasulBera");
        requestJob.setDescription("All_entities");

        Mockito.doNothing().when(requestTaskDao).createRequestTask(requestJob);
        Mockito.when(entityDao.getEntitiesWithShemaNameClient()).thenReturn(new ArrayList<Entity>());
        Mockito.when(requestTaskDao.updateRequestTask(requestJob)).thenReturn(requestJob);
        Mockito.doNothing().when(requestSender).sendRequest(requestJob);

        dataLoaderExecutor.execute(requestJob);
    }

    @Test
    public void testExecuteWithEntities() throws IOException {
        RequestJob actually = new RequestJob();
        actually.setAim("Apply_metadata");
        actually.setStatus(RequestJobStatus.ACTUAL);
        actually.setOwner("VasulBera");
        actually.setDescription("All_entities");

        RequestJob expected = new RequestJob();
        expected.setAim("Apply_metadata");
        expected.setStatus(RequestJobStatus.DONE);
        expected.setOwner("VasulBera");
        expected.setDescription("All_entities");

        Entity entity = new Entity();
        entity.setId("id");
        entity.setFullUploadData(false);
        entity.setTableName("tableName");
        entity.setSchemaName("schemaName");
        entity.setName("name");
        entity.setFieldList(new ArrayList());

        Mockito.doNothing().when(requestTaskDao).createRequestTask(actually);
        Mockito.when(entityDao.getEntitiesWithShemaNameClient()).thenReturn(new ArrayList<Entity>(Arrays.asList(entity)));
        Mockito.doNothing().when(dataLoader).load(entity);
        Mockito.when(requestTaskDao.updateRequestTask(actually)).thenReturn(actually);
        Mockito.doNothing().when(requestSender).sendRequest(actually);

        dataLoaderExecutor.execute(actually);
    }

    @Test
    public void testExecuteWithEntitiesThrowException() throws IOException {
        RequestJob actually = new RequestJob();
        actually.setAim("Apply_metadata");
        actually.setStatus(RequestJobStatus.ACTUAL);
        actually.setOwner("VasulBera");
        actually.setDescription("All_entities");

        RequestJob expected = new RequestJob();
        expected.setAim("Apply_metadata");
        expected.setStatus(RequestJobStatus.ERROR);
        expected.setOwner("VasulBera");
        expected.setDescription("All_entities");

        Entity entity = new Entity();
        entity.setId("id");
        entity.setFullUploadData(false);
        entity.setTableName("tableName");
        entity.setSchemaName("schemaName");
        entity.setName("name");
        entity.setFieldList(new ArrayList());

        Mockito.doNothing().when(requestTaskDao).createRequestTask(actually);
        Mockito.when(entityDao.getEntitiesWithShemaNameClient()).thenReturn(new ArrayList<Entity>(Arrays.asList(entity)));
        Mockito.doThrow(new IOException()).when(dataLoader).load(entity);
        Mockito.when(requestTaskDao.updateRequestTask(actually)).thenReturn(actually);
        Mockito.doNothing().when(requestSender).sendRequest(actually);

        dataLoaderExecutor.execute(actually);
        Assert.assertEquals(expected, actually);
    }
}
