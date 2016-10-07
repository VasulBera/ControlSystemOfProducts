
package com.softserveinc.trainee.test;

import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
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
import javax.ws.rs.ClientErrorException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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

        Field priceField = new Field();
        priceField.setId("CUSTOMPRICE");
        priceField.setColumnName("PRICE");
        priceField.setName("Price");
        priceField.setType(FieldType.NVARCHAR);
        priceField.setLength(45);
        priceField.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Field quantityField = new Field();
        quantityField.setId("CUSTOMQUANTITY");
        quantityField.setColumnName("QUANTITY");
        quantityField.setName("Quantity");
        quantityField.setType(FieldType.INT);
        quantityField.setLength(0);
        quantityField.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        expected.setFieldList(new ArrayList<>(Arrays.asList(priceField, quantityField)));

        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        Entity actually = entityDao.getEntity("CUSTOM");
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetEntityWithoutFields(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        expected.setFieldList(new ArrayList<Field>());
        when(entityManager.find(Entity.class,"CUSTOM")).thenReturn(expected);
        Entity actually = entityDao.getEntity("CUSTOM");
        Assert.assertEquals(expected, actually);
    }

    @Test(expected = ClientErrorException.class)
    public void testDeleteEntityThrowException(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Mockito.when(entityManager.find(Entity.class, expected.getId())).thenReturn(null);
        entityDao.deleteEntity(expected.getId());
     }

    @Test
    public void testDeleteEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Mockito.when(entityManager.find(Entity.class, expected.getId())).thenReturn(expected);
        Mockito.doNothing().when(entityManager).remove(expected);
        entityDao.deleteEntity(expected.getId());
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
        when(entityManager.find(Entity.class, "CUSTOM")).thenReturn(null);
        when(entityManager.merge(expected)).thenReturn(expected);
        Entity actually = entityDao.updateEntity(expected);
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testUpdateEntityFindEntity(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        expected.setFieldList(new ArrayList<Field>());

        when(entityManager.find(Entity.class, "CUSTOM")).thenReturn(expected);
        when(entityManager.merge(expected)).thenReturn(expected);
        Entity actually = entityDao.updateEntity(expected);
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testUpdateEntityEntityWithFields(){
        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Field field = new Field();
        field.setId("CUSTOMERPRICE");
        field.setColumnName("PRICE");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);
        field.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        expected.setFieldList(new ArrayList<Field>(Arrays.asList(field)));

        when(entityManager.find(Entity.class, "CUSTOM")).thenReturn(expected);
        when(entityManager.merge(expected)).thenReturn(expected);
        Entity actually = entityDao.updateEntity(expected);
        Assert.assertEquals(actually, expected);

    }

    @Test
    public void testUpdateEntityEntityWithFieldsForRemove(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");
        Field field = new Field();
        field.setId("CUSTOMERPRICENOTMATCH");
        field.setColumnName("PRICE");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);
        field.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        entity.setFieldList(new ArrayList<Field>(Arrays.asList(field)));

        Entity expected = new Entity();
        expected.setId("CUSTOM");
        expected.setSchemaName("CUSTOMER");
        expected.setTableName("PRODUCT");
        Field expectedField = new Field();
        expectedField.setId("CUSTOMERPRICE");
        expectedField.setColumnName("PRICE");
        expectedField.setType(FieldType.NVARCHAR);
        expectedField.setLength(45);
        expectedField.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        expected.setFieldList(new ArrayList<Field>(Arrays.asList(expectedField)));

        when(entityManager.find(Entity.class, "CUSTOM")).thenReturn(expected);
        when(entityManager.merge(expected)).thenReturn(expected);
        Entity actually = entityDao.updateEntity(entity);
        Assert.assertEquals(actually, expected);

    }

    @Test
    public void testGetAllEntity(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");
        entity.setFieldList(new ArrayList<Field>());
        List<Entity> listEntities = new ArrayList();
        listEntities.add(entity);
        when(entityManager.createQuery("SELECT e FROM Entity e")).thenReturn(query);
        when(query.getResultList()).thenReturn(listEntities);
        List<Entity> actually = entityDao.getAllEntities();
        Assert.assertEquals(listEntities, actually);
    }

    @Test
    public void testGetAllEntityWithComparesing(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("CUSTOMER");
        entity.setTableName("PRODUCT");

        Field priceField = new Field();
        priceField.setId("CUSTOMPRICE");
        priceField.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        Field quantityField = new Field();
        quantityField.setId("CUSTOMQUANTITY");
        quantityField.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        entity.setFieldList(new ArrayList<Field>(Arrays.asList(priceField, quantityField)));
        List<Entity> listEntities = new ArrayList();
        listEntities.add(entity);
        when(entityManager.createQuery("SELECT e FROM Entity e")).thenReturn(query);
        when(query.getResultList()).thenReturn(listEntities);
        List<Entity> actually = entityDao.getAllEntities();
        Assert.assertEquals(listEntities, actually);
    }

    @Test
    public void testGetEntitiesWithShemaNameClientNoEntities(){
        List<Entity> entityList = new ArrayList();
        Mockito.when(entityManager.createQuery("SELECT e FROM Entity e WHERE schema_name = 'client'")).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(entityList);
        List<Entity> expected = new ArrayList();
        List<Entity> actually = entityDao.getEntitiesWithShemaNameClient();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetEntitiesWithShemaNameClient(){
        Field fieldName = new Field();
        fieldName.setCreatedDate(new Timestamp(20_000_000));
        Field fieldPrice = new Field();
        fieldPrice.setCreatedDate(new Timestamp(10_000_000));
        List<Field> fieldList = new ArrayList(Arrays.asList(fieldName, fieldPrice));

        Entity entity = new Entity();
        entity.setFieldList(fieldList);
        List<Entity> entityList = new ArrayList(Arrays.asList(entity));

        Mockito.when(entityManager.createQuery("SELECT e FROM Entity e WHERE schema_name = 'client'")).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(entityList);

        List<Field> expectedFieldList = new ArrayList(Arrays.asList(fieldName, fieldPrice));
        Entity entityExpected = new Entity();
        entityExpected.setFieldList(expectedFieldList);
        List<Entity> expected = new ArrayList(Arrays.asList(entityExpected));
        List<Entity> actually = entityDao.getEntitiesWithShemaNameClient();
        Assert.assertEquals(expected, actually);
    }
}

