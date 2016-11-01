
package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEntity {

    public static void main(String[] args) {
        System.out.println("---->");
    }
    private static Entity entity;

    @BeforeClass
    public static void initializeField(){
        entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("Customer");
        entity.setTableName("entities");
        Field field = new Field();
        field.setColumnName("baba");
        field.setType(FieldType.NVARCHAR);
        field.setLength(89);
        Field field1 = new Field();
        field1.setColumnName("dido");
        field1.setType(FieldType.BIT);
        List<Field> fieldList = new ArrayList();
        fieldList.add(field);
        fieldList.add(field1);
        entity.setFieldList(fieldList);
    }

    @Test
    public void testGetId(){
        String expected = "CUSTOM";
        String actually = entity.getId();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetSchemaName(){
        String expected = "Customer";
        String actually = entity.getSchemaName();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetTableName(){
        String expected = "entities";
        String actually = entity.getTableName();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetId(){
        entity.setId("CUSTOMPRO");
        String expected = "CUSTOMPRO";
        String actually = entity.getId();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetSchemaName(){
        entity.setSchemaName("application");
        String expected = "application";
        String actually = entity.getSchemaName();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetTableName(){
        entity.setTableName("fields");
        String expected = "fields";
        String actually = entity.getTableName();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testNullEquals(){
        boolean expected = false;
        boolean actually = entity.equals(null);
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testNotClassEquals(){
        boolean expected = false;
        boolean actually = entity.equals(new Object());
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testEquals(){
        Entity entityEquals = new Entity();
        entityEquals.setId("CUSTOM");
        entityEquals.setSchemaName("Customer");
        entityEquals.setTableName("entities");

        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("Customer");
        entity.setTableName("entities");

        boolean expected = true;
        boolean actually = entity.equals(entityEquals);
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testNotEquals(){
        Entity entityEquals = new Entity();
        entityEquals.setId("PROVIDER");
        boolean expected = false;
        boolean actually = entity.equals(entityEquals);
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testCreatePreviousStateEntity(){
        Entity entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("Customer");
        entity.setTableName("entities");
        Field field = new Field();
        field.setColumnName("baba");
        field.setType(FieldType.NVARCHAR);
        field.setLength(89);
        Field field1 = new Field();
        field1.setColumnName("dido");
        field1.setType(FieldType.BIT);
        List<Field> fieldList = new ArrayList();
        fieldList.add(field);
        fieldList.add(field1);
        entity.setFieldList(fieldList);

        PreviousStateEntity expected = new PreviousStateEntity();
        expected.setSchemaName("Customer");
        expected.setId("CUSTOM");
        expected.setTableName("entities");
        PreviousStateField previousStateFieldOne = new PreviousStateField();
        previousStateFieldOne.setType(FieldType.NVARCHAR);
        previousStateFieldOne.setColumnName("baba");
        previousStateFieldOne.setLength(89);
        PreviousStateField previousStateFieldTwo = new PreviousStateField();
        previousStateFieldTwo.setType(FieldType.BIT);
        previousStateFieldTwo.setColumnName("dido");
        List<PreviousStateField> preFieldList = new ArrayList();
        preFieldList.add(previousStateFieldOne);
        preFieldList.add(previousStateFieldTwo);
        expected.setFieldList(preFieldList);


        PreviousStateEntity actualy = entity.createPreviousStateEntity();
        Assert.assertEquals(expected, actualy);
    }

    @Test
    public void testIsFullUploadData(){
        Entity entity = new Entity();
        entity.setFullUploadData(true);
        boolean expected = true;
        boolean actually = entity.isFullUploadData();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testSetFullUploadData(){
        Entity entity = new Entity();
        entity.setFullUploadData(true);
        boolean expected = true;
        boolean actually = entity.isFullUploadData();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGenereateShemaWithTable(){
        Entity entity = new Entity();
        entity.setTableName("PRODUCT");
        entity.setSchemaName("client");
        String expected = "client.PRODUCT";
        String actually = entity.genereateShemaWithTable();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testAddLastModifierDateEntityNotMatch(){
        Entity entity = new Entity();
        entity.setId("SPORTGOODS");
        entity.setTableName("GOODS");
        entity.setSchemaName("client");
        entity.setFullUploadData(true);

        Entity persistedEntity = new Entity();
        persistedEntity.setId("SPORTGOODSNOTMATCH");
        persistedEntity.setTableName("GOODS");
        persistedEntity.setSchemaName("client");
        persistedEntity.setFullUploadData(true);

        entity.addLastModifierDate(persistedEntity);
        Timestamp timestamp = entity.getLastModifier();
        Assert.assertNotNull(timestamp);
    }

    @Test
    public void testAddLastModifierDateEntityMatch(){
        Timestamp expected = new Timestamp(System.currentTimeMillis());

        Entity entity = new Entity();
        entity.setId("SPORTGOODS");
        entity.setTableName("GOODS");
        entity.setSchemaName("client");
        entity.setFullUploadData(true);

        Entity persistedEntity = new Entity();
        persistedEntity.setId("SPORTGOODS");
        persistedEntity.setTableName("GOODS");
        persistedEntity.setSchemaName("client");
        persistedEntity.setFullUploadData(true);
        persistedEntity.setLastModifier(expected);

        entity.addLastModifierDate(persistedEntity);
        Timestamp actually = entity.getLastModifier();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetFileName(){
        Entity entity = new Entity();
        entity.setTableName("product");
        String expected = "product.csv";
        String actually = entity.getFileName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testConatraintName(){
        Entity entity = new Entity();
        entity.setTableName("product");
        entity.setSchemaName("client");
        String expected = "client_product_Unique";
        String actually = entity.getConstraintName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testCreateFullTableName(){
        Entity entity = new Entity();
        entity.setTableName("product");
        entity.setSchemaName("client");
        String expected = "[CustomTables].[client].[product]";
        String actually = entity.createFullTableName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetUniqueFieldName(){
        Entity entity = new Entity();
        Field fieldName = new Field();
        fieldName.setColumnName("name");
        fieldName.setUnique(true);
        Field fieldPrice = new Field();
        fieldPrice.setColumnName("price");
        fieldPrice.setUnique(false);
        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName, fieldPrice)));
        String expected = "name";
        String actually = entity.getUniqueFieldName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetUniqueFieldNameNoUniqueName(){
        Entity entity = new Entity();
        Field fieldName = new Field();
        fieldName.setColumnName("name");
        fieldName.setUnique(false);
        Field fieldPrice = new Field();
        fieldPrice.setColumnName("price");
        fieldPrice.setUnique(false);
        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName, fieldPrice)));
        String expected = null;
        String actually = entity.getUniqueFieldName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testJoinColumnNames(){
        Entity entity = new Entity();

        Field fieldName = new Field();
        fieldName.setColumnName("name");

        Field fieldPrice = new Field();
        fieldPrice.setColumnName("price");

        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName, fieldPrice)));
        String expected = "name, price";
        String actually = entity.joinColumnNames();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetTemporaryJoinColumnName(){
        Entity entity = new Entity();

        Field fieldName = new Field();
        fieldName.setColumnName("name");

        Field fieldPrice = new Field();
        fieldPrice.setColumnName("price");

        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName, fieldPrice)));
        String expected = "temporary.name, temporary.price";
        String actually = entity.getTemporaryJoinColumnName();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testChackUniqueFieldNoChanges(){
        Entity entity = new Entity();
        Field fieldName = new Field();
        fieldName.setId("nameId");
        fieldName.setUnique(true);
        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName)));

        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId("nameId");
        previousStateField.setUnique(true);
        previousStateEntity.setFieldList(new ArrayList<>(Arrays.asList(previousStateField)));

        boolean expected = false;
        boolean actually = entity.changeUniqueField(previousStateEntity);
        Assert.assertEquals(expected, actually);

    }

    @Test
    public void testChackUniqueFieldWithChanges(){
        Entity entity = new Entity();
        Field fieldName = new Field();
        fieldName.setId("nameId");
        fieldName.setUnique(false);
        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName)));

        PreviousStateEntity previousStateEntity = new PreviousStateEntity();
        PreviousStateField previousStateField = new PreviousStateField();
        previousStateField.setId("nameId");
        previousStateField.setUnique(true);
        previousStateEntity.setFieldList(new ArrayList<>(Arrays.asList(previousStateField)));

        boolean expected = true;
        boolean actually = entity.changeUniqueField(previousStateEntity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetTmpEntity(){
        Entity entity = new Entity();
        entity.setTableName("product");
        entity.setSchemaName("client");

        Entity expected = new Entity();
        expected.setTableName("product_temporary");
        expected.setSchemaName("client");

        Entity actually = entity.getTmpEntity();
        Assert.assertEquals(expected, actually);
    }


    @AfterClass
    public static void deleteField(){
        entity = null;
    }
}

