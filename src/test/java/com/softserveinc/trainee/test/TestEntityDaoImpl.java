package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.Entity;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityDaoImpl {

    @Mock
    EntityDaoImpl entityDao;

    private static Entity entity;

    @BeforeClass
    public static void initializeEntity(){
        entity = new Entity();
        entity.setId(1);
        entity.setTableName("shoes");
        entity.setSchemaName("barvinoc");
    }

    @Test
    public void testGetEntity(){
        Mockito.when(entityDao.getEntity(1)).thenReturn(entity);
        Entity result = entityDao.getEntity(1);
        Mockito.verify(entityDao).getEntity(1);
        Assert.assertEquals(entity, result);
    }
}
