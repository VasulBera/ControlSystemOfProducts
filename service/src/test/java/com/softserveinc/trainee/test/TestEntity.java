package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestEntity {

    private static Entity entity;

    @BeforeClass
    public static void initializeField(){
        entity = new Entity();
        entity.setId("CUSTOM");
        entity.setSchemaName("Customer");
        entity.setTableName("entities");
        Field field = new Field();
        Field field1 = new Field();
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
    public void testGetFieldList(){
        Field field = new Field();
        Field field1 = new Field();
        List<Field> expected = new ArrayList();
        expected.add(field);
        expected.add(field1);
        List<Field> actually = entity.getFieldList();
        Assert.assertEquals(actually, expected);
    }

    /*@Test
    public void testToString(){
        String expected = "";
        System.out.println(entity);
        String actually = entity.toString();
        Assert.assertEquals(actually, expected);
    }*/

    @Test
    public void testHashCode(){
        int expected = 781176313;
        int actually = entity.hashCode();
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

    @AfterClass
    public static void deleteField(){
        entity = null;
    }
}
