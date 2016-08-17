package com.softserveinc.trainee.test;

import com.softserveinc.trainee.EntityUtil.EntityUtil;
import com.softserveinc.trainee.dao.Impl.EntityDaoImpl;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.entity.FieldType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestField {

    private static Field field;

    @BeforeClass
    public static void initializeField(){
        field = new Field();
        field.setId("QUANTITY");
        field.setName("Quantity");
        field.setType(FieldType.INT);
        field.setLength(45);
    }

    @Test
    public void testGetId(){
        String expected = "QUANTITY";
        Assert.assertEquals(field.getId(), expected);
    }

    @Test
    public void testGetName(){
        String expected = "Quantity";
        Assert.assertEquals(field.getName(), expected);
    }

    @Test
    public void testGetType(){
        FieldType expected = FieldType.INT;
        Assert.assertEquals(field.getType(), expected);
    }

    @Test
    public void testSetId(){
        String expected = "QUANTITYINTR";
        field.setId("QUANTITYINTR");
        Assert.assertEquals(field.getId(), expected);
    }

    @Test
    public void testSetName(){
        String expected = "Price";
        field.setName("Price");
        Assert.assertEquals(field.getName(), expected);
    }

    @Test
    public void testSetType(){
        FieldType expected = FieldType.NVARCHAR;
        field.setType(FieldType.NVARCHAR);
        Assert.assertEquals(field.getType(), expected);
    }

    @Test
    public void testNullEquals(){
        boolean expected = false;
        boolean actually = field.equals(null);
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testNotClassEquals(){
        boolean expected = false;
        boolean actually = field.equals(new Object());
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testEquals(){
        Field fieldEquals = new Field();
        fieldEquals.setId("QUANTITY");
        fieldEquals.setName("Quantity");
        fieldEquals.setType(FieldType.INT);
        fieldEquals.setLength(45);

        Field field = new Field();
        field.setId("QUANTITY");
        field.setName("Quantity");
        field.setType(FieldType.INT);
        field.setLength(45);

        boolean expected = true;
        boolean actually = field.equals(fieldEquals);
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testNotEquals(){
        Field fieldEquals = new Field();
        fieldEquals.setId("QUANTITY");
        boolean expected = false;
        boolean actually = field.equals(fieldEquals);
        Assert.assertEquals(actually, expected);
    }
    @Test
    public void testThisEquals(){
        boolean expected = true;
        boolean actually = field.equals(field);
        Assert.assertEquals(actually, expected);
    }

    @AfterClass
    public static void deleteField(){
        field = null;
    }
}
