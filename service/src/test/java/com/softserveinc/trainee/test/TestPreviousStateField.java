package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.FieldType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestPreviousStateField {

    private static PreviousStateField previousStateField = new PreviousStateField();

    @BeforeClass
    public static void initialize(){
        previousStateField = new PreviousStateField();
        previousStateField.setId("productprice");
        previousStateField.setColumnName("price");
        previousStateField.setName("price");
        previousStateField.setType(FieldType.NVARCHAR);
        previousStateField.setLength(45);
    }

    @Test
    public void testToString(){
        String expected = "PreviousStateField{id='productprice', name='price', ColumnName='price', type=NVARCHAR, length=45}";
        String actually = previousStateField.toString();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testEqualsObjectIsNull(){
        boolean expected = false;
        boolean actually = previousStateField.equals(null);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testEqualsObjectIsTheSame(){
        boolean expected = true;
        boolean actually = previousStateField.equals(previousStateField);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testEqualsObjectDifferentClass(){
        boolean expected = false;
        boolean actually = previousStateField.equals(new String());
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void delete(){
        previousStateField = null;
    }


}
