
package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
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
import java.util.Arrays;
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

    @Test
    public void testGetAllEntities(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");
        List<Entity> expected = new ArrayList();
        expected.add(entity);
        Mockito.when(entityDao.getAllEntities()).thenReturn(expected);
        List<Entity> actually = cxfWebRestService.getAllEntities();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntityWithoutIdWithoutField(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Mockito.when(entityDao.addEntity(any(Entity.class))).thenReturn(entity);
        Entity actually = cxfWebRestService.addEntity(entity);
        Assert.assertEquals(entity, actually);
    }

    @Test
    public void testAddEntityWithIdWithoutField(){
        Entity entity = new Entity();
        entity.setId("ID");
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Mockito.when(entityDao.addEntity(any(Entity.class))).thenReturn(entity);
        Entity actually = cxfWebRestService.addEntity(entity);
        Assert.assertEquals(entity, actually);
    }

    @Test
    public void testAddEntityWithoutIdWithField(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Field field = new Field();
        field.setColumnName("Price");
        entity.setFieldList(new ArrayList<>(Arrays.asList(field)));
        Mockito.when(entityDao.addEntity(any(Entity.class))).thenReturn(entity);
        Entity actually = cxfWebRestService.addEntity(entity);
        Assert.assertEquals(entity, actually);
    }

    @Test
    public void testAddEntityWithIdWithField(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Field field = new Field();
        field.setColumnName("Price");
        field.setId("PRICE");
        entity.setFieldList(new ArrayList<>(Arrays.asList(field)));
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
        expected.setFieldList(new ArrayList<Field>());
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(expected);
        Mockito.when(entityDao.updateEntity(expected)).thenReturn(expected);

        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testUpdateEntityWithoutId(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setFieldList(new ArrayList<Field>());

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(expected);
        Mockito.when(entityDao.updateEntity(expected)).thenReturn(expected);

        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testUpdateEntityWithoutFieldId(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setType(FieldType.INT);
        field.setName("field");
        List<Field> fields = new ArrayList(Arrays.asList(field));
        expected.setFieldList(fields);

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

    @Test
    public void testPatchEntityWithName(){
        Entity entity = new Entity();
        entity.setName("Product");
        Entity expected = new Entity();
        expected.setName("ProductPached");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        Mockito.when(entityDao.updateEntity(any(Entity.class))).thenReturn(expected);
        Entity actually = cxfWebRestService.patchEntity(anyString(), expected);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testPatchEntityWithSchemaName(){
        Entity entity = new Entity();
        entity.setSchemaName("Product");
        Entity expected = new Entity();
        expected.setSchemaName("ProductPached");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        Mockito.when(entityDao.updateEntity(any(Entity.class))).thenReturn(expected);
        Entity actually = cxfWebRestService.patchEntity(anyString(), expected);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testPatchEntityWithTableName(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        Entity expected = new Entity();
        expected.setTableName("ProductPached");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        Mockito.when(entityDao.updateEntity(any(Entity.class))).thenReturn(expected);
        Entity actually = cxfWebRestService.patchEntity(anyString(), expected);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testDeleteEntity(){
        String id = "CUSTOMERID";
        Mockito.doNothing().when(entityDao).deleteEntity(id);
        cxfWebRestService.deleteEntity(id);
    }

    @AfterClass
    public static void delete(){
        entityDao = null;
    }
}

