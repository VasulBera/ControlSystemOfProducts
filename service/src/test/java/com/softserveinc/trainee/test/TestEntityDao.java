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
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityDao {

    @Mock
    private static EntityManager entityManager;

    @Mock
    private static Query query;

    @InjectMocks
    private static EntityDaoImpl entityDao;

    @Before
    public  void doSetup() {
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
        entityDao.deleteEntity(any(Entity.class));
     }

    @Test
    public void testAddEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Mockito.doNothing().when(entityManager).persist(any(Entity.class));
        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        Entity actually = entityDao.addEntity(expected);
        Assert.assertEquals(expected, actually);
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
    @Test
    public void testGetAllEntity(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");
        List<Entity> listEntities = new ArrayList();
        listEntities.add(entity);
        when(entityManager.createQuery("SELECT e FROM Entity e")).thenReturn(query);
        when(query.getResultList()).thenReturn(listEntities);
        List<Entity> actually = entityDao.getAllEntity();
        Assert.assertEquals(listEntities, actually);
    }

}
