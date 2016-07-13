package com.softserveinc.trainee.test;

import com.softserveinc.trainee.mapper.NotFoundExceptionHandler;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class TestNotFoundExceptionHandler {

    private static NotFoundExceptionHandler notFoundExceptionHandler;


    @BeforeClass
    public static void initialize(){
        notFoundExceptionHandler  = new NotFoundExceptionHandler();
    }

    @Test
    public void testToResponce(){
        int expected = Response.status(Response.Status.BAD_REQUEST).build().getStatus();
        int actually = notFoundExceptionHandler.toResponse(new NotFoundException()).getStatus();
        Assert.assertEquals(expected, actually);
    }


    @AfterClass
    public static void delete(){
        notFoundExceptionHandler = null;
    }
}
