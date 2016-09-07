package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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

    @Test
    public void testCreatePreviousStateField(){
        Field field = new Field();
        field.setId("QUANTITY");
        field.setName("Quantity");
        field.setType(FieldType.INT);
        field.setLength(45);

        PreviousStateField expected = new PreviousStateField();
        expected.setId("QUANTITY");
        expected.setName("Quantity");
        expected.setType(FieldType.INT);
        expected.setLength(45);
        PreviousStateField actually = field.createPreviousStateField();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGenerateColumnSqlTypeNvarchar(){
        Field field = new Field();
        field.setColumnName("name");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);
        String expected = "name NVARCHAR(45)";
        String actualy = field.generateColumnSql();
        Assert.assertEquals(expected, actualy);
    }

    @Test
    public void testGenerateColumnSqlTypeInt(){
        Field field = new Field();
        field.setColumnName("name");
        field.setType(FieldType.INT);
        String expected = "name INT";
        String actualy = field.generateColumnSql();
        Assert.assertEquals(expected, actualy);
    }

    @Test
    public void testToString(){
        Field field = new Field();
        field.setId("colorname");
        field.setName("color");
        field.setColumnName("COLOR");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);
        System.out.println(field);
        String expected = "Field{id='colorname', name='color', ColumnName='COLOR', type=NVARCHAR, length=45}";
        String actually = field.toString();
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void deleteField(){
        field = null;
    }
}
