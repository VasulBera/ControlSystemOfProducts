package com.softserveinc.trainee.test;


import com.softserveinc.trainee.applicationConfig.ApplicationContextProvider;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class TestApplicationContextProvider {

    ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();

    @Test
    public void testGetApplicationContext(){
        ApplicationContext actually = applicationContextProvider.getApplicationContext();
        Assert.assertNull(actually);
    }

    @Test
    public void testSetApplicationContext(){
        ApplicationContext contexct = new ApplicationContextProvider().getApplicationContext();
        applicationContextProvider.setApplicationContext(contexct);
        ApplicationContext actually = applicationContextProvider.getApplicationContext();
        ApplicationContext expected = contexct;
        Assert.assertEquals(expected, actually);
    }
}
