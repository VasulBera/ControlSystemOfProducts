package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityDao {

    @Mock
    private static EntityManager entityManager;

    @InjectMocks
    private static EntityDaoImpl entityDao;

    @Before
    public  void doSetup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        Entity actually = entityDao.getEntity("CUSTOM");
        Assert.assertEquals(expected, actually);
    }
}
