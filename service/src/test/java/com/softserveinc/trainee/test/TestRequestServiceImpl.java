package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.RequestTaskDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.service.Impl.RequestServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.ClientErrorException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TestRequestServiceImpl {

    @Mock
    RequestTaskDao requestTaskDao;

    @Mock
    EntityDao entityDao;

    @InjectMocks
    RequestServiceImpl requestService = new RequestServiceImpl();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTask(){
        Mockito.doNothing().when(requestTaskDao).createRequestTask(anyString(), anyString(), anyString());
        requestService.createTask(anyString(), anyString(), anyString());
    }

    @Test
    public void testCreateEntity(){
        Entity entity = new Entity();
        entity.setSchemaName("Schema");
        entity.setTableName("Table");
        entity.setName("Name");

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        Mockito.doNothing().when(requestTaskDao).createEntityTable(any(Entity.class));

        requestService.createEntity(anyString());

    }

    @Test(expected = ClientErrorException.class)
    public void testCreateEntityThrowException(){
        Entity entity = new Entity();
        entity.setSchemaName("Schema");
        entity.setTableName("Table");
        entity.setName("Name");

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        requestService.createEntity(anyString());
    }

}
