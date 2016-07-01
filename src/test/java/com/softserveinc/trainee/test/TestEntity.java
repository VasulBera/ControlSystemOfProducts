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
        entity.setId(1);
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
        Integer expected = 1;
        Integer actually = entity.getId();
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
        entity.setId(2);
        Integer expected = 2;
        Integer actually = entity.getId();
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

    @AfterClass
    public static void deleteField(){
        entity = null;
    }
}
