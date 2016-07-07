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
        field.setId("QUANTITY");
        field.setName("Quantity");
        field.setType("Integer");
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
        String expected = "Integer";
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
        String expected = "Double";
        field.setType("Double");
        Assert.assertEquals(field.getType(), expected);
    }

    @Test
    public void testHashCode(){
        int expected = -168281999;
        int actually = field.hashCode();
        System.out.println(field.hashCode());
        Assert.assertEquals(actually, expected);
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
        fieldEquals.setType("Integer");
        fieldEquals.setLength(45);

        Field field = new Field();
        field.setId("QUANTITY");
        field.setName("Quantity");
        field.setType("Integer");
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
