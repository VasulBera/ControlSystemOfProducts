package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestRequestTask {

    private static RequestJob requestTask;

    @BeforeClass
    public static void initializeRequestTask(){
        requestTask = new RequestJob();
    }

    @Test
    public void testGetId(){
        requestTask.setId(3);
        Integer expected = 3;
        Integer actually = requestTask.getId();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetId(){
        Integer expected = 5;
        requestTask.setId(expected);
        Integer actually = requestTask.getId();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetOwner(){
        requestTask.setOwner("Vasul Bervetskyi");
        String expected = "Vasul Bervetskyi";
        String actually = requestTask.getOwner();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetOwner(){
        String expected = "Vasul Bervetskyi";
        requestTask.setOwner(expected);
        String actually = requestTask.getOwner();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetAim(){
        requestTask.setAim("all_entity");
        String expected = "all_entity";
        String actually = requestTask.getAim();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetAim(){
        String expected = "all_entity";
        requestTask.setAim(expected);
        String actually = requestTask.getAim();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetDescription(){
        requestTask.setDescription("apply_metadata");
        String expected = "apply_metadata";
        String actually = requestTask.getDescription();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetDescription(){
        String expected = "apply_metadata";
        requestTask.setDescription(expected);
        String actually = requestTask.getDescription();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testGetStatus(){
        requestTask.setStatus(RequestJobStatus.ACTUAL);
        RequestJobStatus expected = RequestJobStatus.ACTUAL;
        RequestJobStatus actually = requestTask.getStatus();
        Assert.assertEquals(actually, expected);
    }

    @Test
    public void testSetStatus(){
        RequestJobStatus expected = RequestJobStatus.ERROR;
        requestTask.setStatus(expected);
        RequestJobStatus actually = requestTask.getStatus();
        Assert.assertEquals(actually, expected);
    }

    @AfterClass
    public static void deleteRequestTask(){
        requestTask = null;
    }
}
