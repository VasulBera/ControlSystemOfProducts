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
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TestCxfWebRestServiceImpl {

    @Mock
    private static EntityDao entityDao;

    @InjectMocks
    private static CxfWebRestServiceImpl cxfWebRestService = new CxfWebRestServiceImpl();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntity(){
        Entity expected = new Entity();
        expected.setId("ADIDAS");
        Mockito.when(entityDao.getEntity("ADIDAS")).thenReturn(expected);
        Entity actually = cxfWebRestService.getEntity("ADIDAS");
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntity(){
        Entity expected = new Entity();
        expected.setId("ADIDAS");
        Mockito.when(entityDao.addEntity(expected)).thenReturn(expected);
        Entity actually = cxfWebRestService.addEntity(expected);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testUpdateEntity(){
        Entity expected = new Entity();
        expected.setId("ADIDAS");
        Mockito.when(entityDao.updateEntity(expected)).thenReturn(expected);
        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testDeleteEntity(){
        Mockito.doNothing().when(entityDao).deleteEntity(anyString());
        cxfWebRestService.deleteEntity(anyString());
    }

    @AfterClass
    public static void delete(){
        entityDao = null;
    }
}
