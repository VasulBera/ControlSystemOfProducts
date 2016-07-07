package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.Entity;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityDao {

    private static EntityDaoImpl entityDao;


    @BeforeClass
    public static void doSetup(){
        entityDao = mock(EntityDaoImpl.class);
    }

    @Test
    public void testGetEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        when(entityDao.getEntity("CUSTOM")).thenReturn(expected);
        Entity actually = entityDao.getEntity("CUSTOM");
        verify(entityDao).getEntity("CUSTOM");
        Assert.assertEquals(expected, actually);
    }
}
