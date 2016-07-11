package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.service.CxfWebRestServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestCxfWebRestServiceImpl {

    @Mock
    private static EntityDao entityDao;

    @InjectMocks
    private static CxfWebRestServiceImpl cxfWebRestService;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntity(){
        Entity entity = new Entity();
        entity.setId("ADIDAS");
        Mockito.when(entityDao.getEntity("ADIDAS")).thenReturn(entity);
        Entity actually = cxfWebRestService.getEntity("ADIDAS");
        Assert.assertEquals(entity, actually);
    }

    @AfterClass
    public static void delete(){
        entityDao = null;
    }
}
