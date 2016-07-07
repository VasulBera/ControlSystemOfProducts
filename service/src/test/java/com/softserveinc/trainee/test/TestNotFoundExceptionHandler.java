package com.softserveinc.trainee.test;

import com.softserveinc.trainee.mapper.NotFoundExceptionHandler;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Created by vberv on 7/7/2016.
 */
public class TestNotFoundExceptionHandler {

    NotFoundExceptionHandler notFoundExceptionHandler = new NotFoundExceptionHandler();

    @Test
    public void testToResponce(){
        int expected = Response.status(Response.Status.BAD_REQUEST).build().getStatus();
        int actually = notFoundExceptionHandler.toResponse(new NotFoundException()).getStatus();
        Assert.assertEquals(expected, actually);
    }
}
