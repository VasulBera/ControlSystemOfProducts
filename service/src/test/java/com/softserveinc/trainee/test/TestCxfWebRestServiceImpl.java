package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.entity.FieldType;
import com.softserveinc.trainee.service.CxfWebRestServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class TestCxfWebRestServiceImpl {

    private static int LIMIT = 129;
    private static String TEST_STRING = RandomStringUtils.random(LIMIT);

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

    @Test(expected = NotFoundException.class)
    public void testAddEntityWithNullTableName(){
        Entity entity = new Entity();
        entity.setSchemaName("");
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityWithNullSchemaName(){
        Entity entity = new Entity();
        entity.setTableName("");
        entity.setId("");
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityWithNotValideSchemaName(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("*/");
        cxfWebRestService.addEntity(entity);
    }
    @Test(expected = NotFoundException.class)
    public void testAddEntityWithNotValideTableName(){
        Entity entity = new Entity();
        entity.setTableName("/*");
        entity.setSchemaName("Customer");
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityWithLengthTableNameEquals129(){
        Entity entity = new Entity();
        entity.setTableName(TEST_STRING);
        entity.setSchemaName("Customer");
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityWithLengthSchemaNameEquals129(){
        Entity entity = new Entity();
        entity.setTableName("Customer");
        entity.setSchemaName(TEST_STRING);
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityExistingEntity(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entity);
        cxfWebRestService.addEntity(entity);
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

    @Test(expected = NotFoundException.class)
    public void testAddEntityNullFiledName(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setType(FieldType.NVARCHAR);
        field.setLength(78);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityNullFiledType(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setName("price");
        field.setLength(78);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityFiledNameLangthEquals129(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setType(FieldType.NVARCHAR);
        field.setName(TEST_STRING);
        field.setLength(78);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityFiledNameNotValid(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setType(FieldType.NVARCHAR);
        field.setName("*//*//*");
        field.setLength(78);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.addEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testAddEntityFiledLengthLessThan0(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");

        Field field = new Field();
        field.setType(FieldType.NVARCHAR);
        field.setName("price");
        field.setLength(-1);

        List<Field> list = new ArrayList();
        list.add(field);
        entity.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.addEntity(entity);
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

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithNullTableName(){
        Entity entity = new Entity();
        entity.setSchemaName("Customer");
        entity.setId("CustomerProduct");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithNullSchemaName(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setId("CustomerProduct");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithNullId(){
        Entity entity = new Entity();
        entity.setTableName("Product");
        entity.setSchemaName("Customer");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithNotValideSchemaName(){
        Entity entity = new Entity();
        entity.setId("CustomerProduct");
        entity.setTableName("Product");
        entity.setSchemaName("*//*");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithNotValideTableName(){
        Entity entity = new Entity();
        entity.setId("CustomerProduct");
        entity.setTableName("*//*");
        entity.setSchemaName("Customer");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithLengthTableNameEquals129(){
        Entity entity = new Entity();
        entity.setId("CustomerProduct");
        entity.setTableName(TEST_STRING);
        entity.setSchemaName("Customer");
        cxfWebRestService.updateEntity(entity);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityWithLengthSchemaNameEquals129(){
        Entity entity = new Entity();
        entity.setId("CustomerProduct");
        entity.setTableName("Customer");
        entity.setSchemaName(TEST_STRING);
        cxfWebRestService.updateEntity(entity);
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


    @Test
    public void testUpdateEntityGotEntityIsNull(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        Mockito.when(entityDao.addEntity(expected)).thenReturn(expected);

        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testUpdateEntityGotEntityIsNullWithField(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setType(FieldType.NVARCHAR);
        field.setName("price");
        field.setLength(1);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        Mockito.when(entityDao.addEntity(expected)).thenReturn(expected);

        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityGotIsNullFieldNameIsNull(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setType(FieldType.NVARCHAR);
        field.setLength(1);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        cxfWebRestService.updateEntity(expected);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityGotIsNullFieldNameIsNotValid(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setName("*//*");
        field.setType(FieldType.NVARCHAR);
        field.setLength(1);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        cxfWebRestService.updateEntity(expected);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityGotIsNullFieldNameLengthMoreThan129(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setName(TEST_STRING);
        field.setType(FieldType.NVARCHAR);
        field.setLength(1);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        cxfWebRestService.updateEntity(expected);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateEntityGotIsNullFieldLengthLessThat0(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        expected.setSchemaName("Customer");
        expected.setId("CustomerProduct");

        Field field = new Field();
        field.setId("CustomerProductPrice");
        field.setName("Price");
        field.setType(FieldType.NVARCHAR);
        field.setLength(-1);

        List<Field> list = new ArrayList();
        list.add(field);
        expected.setFieldList(list);

        cxfWebRestService.updateEntity(expected);
    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntityNull(){
        Entity entity = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(null);
        cxfWebRestService.patchEntity("ADIDAS", entity);
    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntitySchemaNameNotNullDoesNotMatchRegex(){
        Entity entity = new Entity();
        entity.setSchemaName("*");
        Entity entityDb = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entityDb);
        cxfWebRestService.patchEntity("ADIDAS", entity);
    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntitySchemaNameNotNullMatchRegex(){
        Entity expected = new Entity();
        expected.setSchemaName("Customer");
        Entity entityDb = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entityDb);
        cxfWebRestService.patchEntity("ADIDAS", expected);
        Mockito.when(entityDao.updateEntity(entityDb)).thenReturn(entityDb);
        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);
    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntityTableNameNotNullDoesNotMatchRegex(){
        Entity entity = new Entity();
        entity.setTableName("*");
        Entity entityDb = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entityDb);
        cxfWebRestService.patchEntity("ADIDAS", entity);
    }

    @Test(expected = ClientErrorException.class)
    public void testPatchEntityTableNameNotNullMatchRegex(){
        Entity expected = new Entity();
        expected.setTableName("Product");
        Entity entityDb = new Entity();
        Mockito.when(entityDao.getEntity(anyString())).thenReturn(entityDb);
        cxfWebRestService.patchEntity("ADIDAS", expected);
        Mockito.when(entityDao.updateEntity(entityDb)).thenReturn(entityDb);
        Entity actually = cxfWebRestService.updateEntity(expected);
        Assert.assertEquals(expected, actually);
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
