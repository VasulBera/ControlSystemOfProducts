package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.EntityTimeStamp;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

public class TestTimeStamp {

    private static EntityTimeStamp entityTimeStamp;

    @BeforeClass
    public static void initializeTimeStamp(){
        entityTimeStamp = new EntityTimeStamp();
    }

    @Test
    public void testGetCreatedDate(){
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        entityTimeStamp.setCreatedDate(expected);
        Timestamp actually = entityTimeStamp.getCreatedDate();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetLastModifier(){
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        entityTimeStamp.setLastModifier(expected);
        Timestamp actually = entityTimeStamp.getLastModifier();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testSetCreatedDate(){
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        entityTimeStamp.setCreatedDate(expected);
        Timestamp actually = entityTimeStamp.getCreatedDate();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testSetLastModifier(){
        Timestamp expected = new Timestamp(System.currentTimeMillis());
        entityTimeStamp.setLastModifier(expected);
        Timestamp actually = entityTimeStamp.getLastModifier();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testOnCreate(){
        EntityTimeStamp entityTimeStamp = new EntityTimeStamp();
        entityTimeStamp.onCreate();
        Timestamp timestamp = entityTimeStamp.getCreatedDate();
        Assert.assertNotNull(timestamp);
    }

    @AfterClass
    public static void deleteTimeStamp(){
        entityTimeStamp = null;
    }


}
