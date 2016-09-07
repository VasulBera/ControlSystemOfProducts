package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.Impl.RequestJobDaoImpl;
import com.softserveinc.trainee.entity.administration.RequestJob;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

public class TestRequestTaskDaoImpl {

    @Mock
    private static EntityManager entityManager;

    @InjectMocks
    private static RequestJobDaoImpl requestTaskDao;

    @Before
    public  void doSetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRequestTask(){
        RequestJob requestTask = new RequestJob();
        Mockito.doNothing().when(entityManager).persist(requestTask);
        requestTaskDao.createRequestTask(requestTask);
    }

    @Test
    public void testUpdateRequestTask(){
        RequestJob requestTask = new RequestJob();
        Mockito.when(entityManager.merge(requestTask)).thenReturn(requestTask);
        requestTaskDao.updateRequestTask(requestTask);
    }

}
