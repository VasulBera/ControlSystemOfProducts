
package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.entity.FieldType;
import com.softserveinc.trainee.service.Impl.EntityServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import javax.ws.rs.ClientErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TestEntityServiceImpl {

    @Mock
    private static EntityDao entityDao;

    @InjectMocks
    private static EntityServiceImpl cxfWebRestService = new EntityServiceImpl();

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

    @Test(expected = ClientErrorException.class)
    public void testGetEntityIsNull(){
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.getEntity(anyString());
    }

    @Test
    public void testGetAllEntities(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");
        List<Entity> expected = new ArrayList();
        expected.add(entity);
        Mockito.when(entityDao.getAllEntity()).thenReturn(expected);
        List<Entity> actually = cxfWebRestService.getAllEntities();
        Assert.assertEquals(expected, actually);
    }

    @Test(expected = ClientErrorException.class)
    public void testGetAllEntityRetunrEmptyList(){
        Mockito.when(entityDao.getAllEntity()).thenReturn(new ArrayList<Entity>());
        cxfWebRestService.getAllEntities();
    }

    @Test
    public void testAddEntityWithoutField(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        Mockito.when(entityDao.addEntity(any(Entity.class))).thenReturn(entity);
        Entity actually = cxfWebRestService.addEntity(entity);
        Assert.assertEquals(entity, actually);
    }

@Test
    public void testAddEntity(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setType(FieldType.NVARCHAR);
        field.setName("price");
        field.setLength(1);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        Mockito.when(entityDao.addEntity(any(Entity.class))).thenReturn(entity);
        Entity actually = cxfWebRestService.addEntity(entity);
        Assert.assertEquals(entity, actually);
    }

    @Test
    public void testUpdateEntity(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(expected);
        Mockito.when(entityDao.updateEntity(expected)).thenReturn(expected);

        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntityNull(){
        Entity entity = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.patchEntity("ADIDAS", entity);
    }

    @Test
    public void testPatchEntityEntityHasField(){
        Entity expected = new Entity();
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setName("Price");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        Entity enteredEntity = new Entity();
        enteredEntity.setId("CustomerProduct");

        Field enteredEntityField = new Field();
        enteredEntityField.setId("CustomerProductPrice");
        enteredEntityField.setName("Price");
        enteredEntityField.setType(FieldType.NVARCHAR);
        enteredEntityField.setLength(1);

        List<Field> enteredentityFieldList = new ArrayList();
        enteredentityFieldList.add(enteredEntityField);
        enteredEntity.setFieldList(enteredentityFieldList);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(expected);
        Mockito.when(entityDao.updateEntity(any(Entity.class))).thenReturn(enteredEntity);
        Entity actually = cxfWebRestService.patchEntity("CustomerProduct", enteredEntity);
        System.out.println(actually);
        Assert.assertEquals(actually, enteredEntity);
    }

    @Test(expected = ClientErrorException.class)
    public void testDeleteEntityNull(){
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.deleteEntity(anyString());
    }

    @Test
    public void testDeleteEntity(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        Mockito.doNothing().when(entityDao).deleteEntity(any(Entity.class));
        cxfWebRestService.deleteEntity(anyString());
    }

    @AfterClass
    public static void delete(){
        entityDao = null;
    }
}

