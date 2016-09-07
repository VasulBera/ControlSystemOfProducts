package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.TimeStamp;
import com.softserveinc.trainee.entity.metadata.Entity;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class TestTimeStamp {

    private static TimeStamp timeStamp;

    @BeforeClass
    public static void initializeTimeStamp(){
        timeStamp = new TimeStamp();
    }

    @Test
    public void testGetCreate_time(){
        timeStamp.setCreatedDate(new Date(131313));
        Date expected = new Date(131313);
        Date actualy = timeStamp.getCreatedDate();
        Assert.assertEquals(actualy, expected);
    }

    @Test
    public void testSetCreate_time(){
        Date expected = new Date(131314);
        timeStamp.setCreatedDate(expected);
        Date actualy = timeStamp.getCreatedDate();
        Assert.assertEquals(actualy, expected);
    }

    @Test
    public void testGetUpdate_time(){
        timeStamp.setLastModifier(new Date(262626));
        Date expected = new Date(262626);
        Date actualy = timeStamp.getLastModifier();
        Assert.assertEquals(actualy, expected);
    }

    @Test
    public void testSetUpdate_time(){
        Date expected = new Date(262627);
        timeStamp.setLastModifier(expected);
        Date actualy = timeStamp.getLastModifier();
        Assert.assertEquals(actualy, expected);
    }

    @Test
    public void testSetLastModifier(){
        TimeStamp timeStamp = new TimeStamp();
        timeStamp.onCreate();
        Date date = timeStamp.getCreatedDate();
        Assert.assertNotNull(date);
    }

    @Test
    public void testSetLastModifierOnChange(){
        TimeStamp timeStamp = new TimeStamp();
        timeStamp.setLastModifierOnChange();
        Date date = timeStamp.getLastModifier();
        Assert.assertNotNull(date);
    }

    @AfterClass
    public static void deleteTimeStamp(){
        timeStamp = null;
    }


}
