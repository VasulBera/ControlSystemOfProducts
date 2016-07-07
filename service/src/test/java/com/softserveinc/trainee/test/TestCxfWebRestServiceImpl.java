package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.service.CxfWebRestServiceImpl;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

public class TestCxfWebRestServiceImpl {

   private static EntityDao entityDao;
    private static CxfWebRestServiceImpl cxfWebRestService;

    @BeforeClass
    public static void initialize(){
        entityDao = PowerMockito.mock(EntityDao.class);
    }

    @Test
    public void testGetEntity(){
        Entity entity = new Entity();
        entity.setId("ADIDAS");
        PowerMockito.when(entityDao.getEntity("ADIDAS")).thenReturn(entity);
        Entity actually = entityDao.getEntity("ADIDAS");
        Mockito.verify(entityDao).getEntity("ADIDAS");
        Assert.assertEquals(entity, actually);
    }

    @AfterClass
    public static void delete(){
        entityDao = null;
    }
}
