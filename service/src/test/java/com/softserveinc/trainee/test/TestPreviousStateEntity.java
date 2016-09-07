package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void testHashCode(){
        Integer expected = 515567022;
        Integer actually = previousStateEntity.hashCode();
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

    @AfterClass
    public static void delete(){
        previousStateEntity = null;
    }



}
