package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.Entity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import javax.persistence.EntityManager;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

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
    @Test
    public void testDeleteEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Mockito.doNothing().when(entityManager).remove(any(Entity.class));
        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        entityDao.deleteEntity("CUSTOM");
     }

    @Test
    public void testAddEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Mockito.doNothing().when(entityManager).persist(any(Entity.class));
        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        entityDao.addEntity(expected);
    }

    @Test
    public void testUpdateEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        when(entityManager.merge(expected)).thenReturn(expected);
        Entity actually = entityDao.updateEntity(expected);
        Assert.assertEquals(actually, expected);
    }

}
