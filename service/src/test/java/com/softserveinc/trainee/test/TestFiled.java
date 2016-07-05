package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.Field;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFiled {

    private static Field field;

    @BeforeClass
    public static void initializeField(){
        field = new Field();
        field.setId(1);
        field.setName("Quantity");
        field.setType("Integer");
        field.setLength(45);
    }

    @Test
    public void testGetId(){
        Integer expected = 1;
        Assert.assertEquals(field.getId(), expected);
    }

    @Test
    public void testGetName(){
        String expected = "Quantity";
        Assert.assertEquals(field.getName(), expected);
    }

    @Test
    public void testGetType(){
        String expected = "Integer";
        Assert.assertEquals(field.getType(), expected);
    }

    @Test
    public void testSetId(){
        Integer expected = 2;
        field.setId(2);
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
        String expected = "Double";
        field.setType("Double");
        Assert.assertEquals(field.getType(), expected);
    }

    @AfterClass
    public static void deleteField(){
        field = null;
    }
}
