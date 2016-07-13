package com.softserveinc.trainee.test;

import com.softserveinc.trainee.mapper.WebApplicationExceptionHandler;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by vberv on 7/13/2016.
 */
public class TestWebApplicationExceptionHandler {

   private static WebApplicationExceptionHandler webApplicationExceptionHandler;

    @BeforeClass
    public static void initialize(){
        webApplicationExceptionHandler = new WebApplicationExceptionHandler();
    }

    @Test
    public void testToResponse(){
        int expected = Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build().getStatus();
        int actually = webApplicationExceptionHandler.toResponse(new WebApplicationException()).getStatus();
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void delete(){
        webApplicationExceptionHandler = null;
    }


}
