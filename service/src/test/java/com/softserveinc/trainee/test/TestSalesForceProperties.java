package com.softserveinc.trainee.test;


import com.softserveinc.trainee.sender.Impl.salesforce.SalesForceProperties;
import org.junit.Assert;
import org.junit.Test;

public class TestSalesForceProperties {

    @Test
    public void testGetValue(){
        String expected = "client_id";
        SalesForceProperties property = SalesForceProperties.CLIENT_ID_PREFIX;
        String actually = property.getValue();
        Assert.assertEquals(expected, actually);
    }
}
