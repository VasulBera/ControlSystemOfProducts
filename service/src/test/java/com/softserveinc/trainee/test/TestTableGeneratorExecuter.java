package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.Impl.TableGeneratorExecutor;
import com.softserveinc.trainee.generator.TableGenerator;
import com.softserveinc.trainee.sender.RequestSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

public class TestTableGeneratorExecuter {

    @Mock
    RequestJobDao requestJobDao;

    @Mock
    EntityDao entityDao;

    @Mock
    PreviousStateEntityDao previousStateEntityDao;

    @Mock
    TableGenerator tableGenerator;

    @Mock
    RequestSender requestSender;

    @InjectMocks
    TableGeneratorExecutor tableGeneratorExecutor = new TableGeneratorExecutor();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteNoEnities(){
        RequestJobStatus expected = RequestJobStatus.DONE;
        RequestJob job = new RequestJob();
        job.setOwner("VasulBera");
        job.setDescription("Apply_data");
        job.setAim("All_entities");
        job.setStatus(RequestJobStatus.ACTUAL);
        Mockito.when(entityDao.getAllEntities()).thenReturn(new ArrayList());
        Mockito.when(requestJobDao.updateRequestTask(job)).thenReturn(job);
        Mockito.doNothing().when(requestSender).sendRequest(job);
        tableGeneratorExecutor.execute(job);
        RequestJobStatus actually = job.getStatus();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testExecuteCreateTable(){
        RequestJobStatus expected = RequestJobStatus.DONE;
        RequestJob job = new RequestJob();
        job.setOwner("VasulBera");
        job.setDescription("Apply_data");
        job.setAim("All_entities");
        job.setStatus(RequestJobStatus.ACTUAL);

        Entity entity = new Entity();
        entity.setId("id");
        entity.setFullUploadData(false);
        entity.setTableName("tableName");
        entity.setSchemaName("schemaName");
        entity.setName("name");
        entity.setFieldList(new ArrayList());

        Mockito.when(entityDao.getAllEntities()).thenReturn(new ArrayList<Entity>(Arrays.asList(entity)));
        Mockito.when(previousStateEntityDao.addPreviousStateEntity(entity.createPreviousStateEntity())).thenReturn(false);
        Mockito.when(requestJobDao.updateRequestTask(job)).thenReturn(job);
        Mockito.doNothing().when(requestSender).sendRequest(job);
        Mockito.doNothing().when(tableGenerator).createTable(entity);
        tableGeneratorExecutor.execute(job);
    }

    @Test
    public void testExecuteUpdateTable(){
        RequestJobStatus expected = RequestJobStatus.DONE;
        RequestJob job = new RequestJob();
        job.setOwner("VasulBera");
        job.setDescription("Apply_data");
        job.setAim("All_entities");
        job.setStatus(RequestJobStatus.ACTUAL);

        Entity entity = new Entity();
        entity.setId("id");
        entity.setFullUploadData(false);
        entity.setTableName("tableName");
        entity.setSchemaName("schemaName");
        entity.setName("name");
        entity.setFieldList(new ArrayList());

        Mockito.when(entityDao.getAllEntities()).thenReturn(new ArrayList<Entity>(Arrays.asList(entity)));
        Mockito.when(previousStateEntityDao.addPreviousStateEntity(entity.createPreviousStateEntity())).thenReturn(true);
        Mockito.when(previousStateEntityDao.getPreviousStateEntity(entity.getId())).thenReturn(entity.createPreviousStateEntity());
        Mockito.doNothing().when(tableGenerator).updateTable(entity.createPreviousStateEntity(), entity);
        Mockito.when(previousStateEntityDao.updatePreviousStateEntity(entity.createPreviousStateEntity())).thenReturn(entity.createPreviousStateEntity());
        tableGeneratorExecutor.execute(job);
    }
}
