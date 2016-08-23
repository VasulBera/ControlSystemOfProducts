
package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @AfterClass
    public static void deleteField(){
        entity = null;
    }
}

