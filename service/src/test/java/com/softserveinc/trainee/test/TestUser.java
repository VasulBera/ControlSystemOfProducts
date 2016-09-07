package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.User;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestUser {

    private static User user;

    @BeforeClass
    public static void initializUser(){
        user = new User();
        user.setId(12);
        user.setClientId("clientId");
        user.setClientSecret("clientSecret");
        user.setGrantType("security");
        user.setPassword("pasword");
        user.setSecurityToken("securityToken");
        user.setUsername("username");
    }

    @Test
    public void testGetId(){
        Integer expected = 12;
        Integer actually = user.getId();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetUsername(){
        String expected = "username";
        String actually = user.getUsername();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetPassword(){
        String expected = "pasword";
        String actually = user.getPassword();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetSecurityToken(){
        String expected = "securityToken";
        String actually = user.getSecurityToken();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetClientId(){
        String expected = "clientId";
        String actually = user.getClientId();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetClientSecret(){
        String expected = "clientSecret";
        String actually = user.getClientSecret();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testGetGrantType(){
        String expected = "security";
        String actually = user.getGrantType();
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testPaswordWithSecToken(){
        String expected = "paswordsecurityToken";
        String actually = user.paswordWithSecToken();
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void deleteUser(){
        user = null;
    }
}
