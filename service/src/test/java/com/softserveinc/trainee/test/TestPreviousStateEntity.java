package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPreviousStateEntity {

    private static PreviousStateEntity previousStateEntity;

    @BeforeClass
    public static void initialize(){
        previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setFullUploadData(true);
        previousStateEntity.setTableName("PRODUCT");
        previousStateEntity.setSchemaName("client");
        previousStateEntity.setName("product");
        previousStateEntity.setId("CLIENTPRODUCT");
    }

    @Test
    public void testIsFullUploadData(){
        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setFullUploadData(true);
        boolean expected = true;
        boolean actually = previousStateEntity.isFullUploadData();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testSetFullUploadData(){
        boolean expected = false;
        previousStateEntity.setFullUploadData(false);
        boolean actually = previousStateEntity.isFullUploadData();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testToString(){
        String expected = "PreviousStateEntity{id='CLIENTPRODUCT', name='product', schemaName='client', tableName='PRODUCT', fieldList=null}";
        String actually = previousStateEntity.toString();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testEqualsObjectIsNull(){
        boolean expected = false;
        boolean actually = previousStateEntity.equals(null);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testEqualsDifferentClass(){
        boolean expected = false;
        boolean actually = previousStateEntity.equals(new String());
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddLastModifierDate(){
        Timestamp expected = new Timestamp(10_000_000);
        PreviousStateEntity entity = new PreviousStateEntity();
        entity.setId("entityId");

        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setId("entityId");
        previousStateEntity.setLastModifier(expected);

        entity.addLastModifierDate(previousStateEntity);
        Timestamp actually = entity.getLastModifier();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetFieldsNoFields(){
        PreviousStateEntity entity = new PreviousStateEntity();
        entity.setFieldList(new ArrayList());
        List<Field> expected = new ArrayList();
        List<Field> actually = entity.getFields();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetFields(){
        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId("fieldId");
        previousStateField.setColumnName("culumnName");
        previousStateField.setLength(10);
        previousStateField.setName("name");
        previousStateField.setType(FieldType.NVARCHAR);
        previousStateField.setUnique(true);
        PreviousStateEntity entity = new PreviousStateEntity();
        entity.setFieldList(new ArrayList(Arrays.asList(previousStateField)));
        Field field = new Field();
        field.setId("fieldId");
        field.setColumnName("culumnName");
        field.setLength(10);
        field.setName("name");
        field.setType(FieldType.NVARCHAR);
        field.setUnique(true);
        List<Field> expected = new ArrayList(Arrays.asList(field));
        List<Field> actually = entity.getFields();
        Assert.assertEquals(expected, actually);
    }



    @AfterClass
    public static void delete(){
        previousStateEntity = null;
    }

}
