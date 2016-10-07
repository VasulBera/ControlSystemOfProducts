package com.softserveinc.trainee.test;


import com.softserveinc.trainee.dao.Impl.PreviousStateEntityDaoImpl;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class TestPreviousStateEntityDaoImpl {

    @Mock
    private static EntityManager entityManager;

    @InjectMocks
    private static PreviousStateEntityDaoImpl previousStateEntityDao;

    @Before
    public  void doSetup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetEntity(){
        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, expected.getId())).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.getPreviousStateEntity(expected.getId());
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntityThatExists(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(previousStateEntity);
        boolean expected = true;
        boolean actually = previousStateEntityDao.addPreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddEntityThatNotExists(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Cuatomer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(null);
        Mockito.doNothing().when(entityManager).persist(previousStateEntity);
        boolean expected = false;
        boolean actually = previousStateEntityDao.addPreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testUpdateEntityFindNull(){
        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Customer");
        Mockito.when(entityManager.find(PreviousStateEntity.class, expected.getId())).thenReturn(null);
        Mockito.when(entityManager.merge(expected)).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.updatePreviousStateEntity(expected);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testUpdateEntityFindPreviusStateEntityWithoutField(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Customer");
        previousStateEntity.setName("Product");
        previousStateEntity.setSchemaName("client");
        previousStateEntity.setTableName("PRODUCT");
        previousStateEntity.setFieldList(new ArrayList<PreviousStateField>());

        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Customer");
        expected.setTableName("PRODUCT_EDIT");
        expected.setSchemaName("client_EDIT");
        expected.setName("Product");
        expected.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        expected.setFieldList(new ArrayList<PreviousStateField>());

        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(expected);
        Mockito.when(entityManager.merge(previousStateEntity)).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.updatePreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testUpdateEntityFindPreviusStateEntityWithTheSameIdFieldInBothEntity(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Customer");
        previousStateEntity.setName("Product");
        previousStateEntity.setSchemaName("client");
        previousStateEntity.setTableName("PRODUCT");

        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId("CUSTOMERPRICE");
        previousStateField.setName("Price");
        previousStateField.setType(FieldType.NVARCHAR);
        previousStateField.setLength(45);
        previousStateField.setColumnName("PRICE");
        previousStateEntity.setFieldList(new ArrayList<PreviousStateField>(Arrays.asList(previousStateField)));

        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Customer");
        expected.setTableName("PRODUCT_EDIT");
        expected.setSchemaName("client_EDIT");
        expected.setName("Product");
        expected.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        PreviousStateField expectedField = new PreviousStateField();
        expectedField.setId("CUSTOMERPRICE");
        expectedField.setName("Price");
        expectedField.setType(FieldType.NVARCHAR);
        expectedField.setLength(45);
        expectedField.setColumnName("PRICE_EDIT");
        expected.setFieldList(new ArrayList<PreviousStateField>(Arrays.asList(expectedField)));

        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(expected);
        Mockito.when(entityManager.merge(previousStateEntity)).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.updatePreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testUpdateEntityFindPreviusStateEntityWithFieldIdDoesNotMatch(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("Customer");
        previousStateEntity.setName("Product");
        previousStateEntity.setSchemaName("client");
        previousStateEntity.setTableName("PRODUCT");

        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId("CUSTOMERPRICENOTMATCH");
        previousStateField.setName("Price");
        previousStateField.setType(FieldType.NVARCHAR);
        previousStateField.setLength(45);
        previousStateField.setColumnName("PRICE");
        previousStateEntity.setFieldList(new ArrayList<PreviousStateField>(Arrays.asList(previousStateField)));

        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setId("Customer");
        expected.setTableName("PRODUCT_EDIT");
        expected.setSchemaName("client_EDIT");
        expected.setName("Product");
        expected.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        PreviousStateField expectedField = new PreviousStateField();
        expectedField.setId("CUSTOMERPRICE");
        expectedField.setName("Price");
        expectedField.setType(FieldType.NVARCHAR);
        expectedField.setLength(45);
        expectedField.setColumnName("PRICE_EDIT");
        expected.setFieldList(new ArrayList<PreviousStateField>(Arrays.asList(expectedField)));

        Mockito.doNothing().when(entityManager).remove(expectedField);
        Mockito.when(entityManager.find(PreviousStateEntity.class, previousStateEntity.getId())).thenReturn(expected);
        Mockito.when(entityManager.merge(previousStateEntity)).thenReturn(expected);
        PreviousStateEntity actually = previousStateEntityDao.updatePreviousStateEntity(previousStateEntity);
        Assert.assertEquals(expected, actually);

    }
}
