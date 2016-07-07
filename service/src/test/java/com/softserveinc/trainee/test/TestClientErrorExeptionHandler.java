package com.softserveinc.trainee.test;

import com.softserveinc.trainee.mapper.ClientErrorExeptionHandler;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Created by vberv on 7/7/2016.
 */
public class TestClientErrorExeptionHandler {

    ClientErrorExeptionHandler clientErrorExeptionHandler = new ClientErrorExeptionHandler();

    @Test
    public void testToResponse(){
        int expected = Response.status(Response.Status.NOT_FOUND).build().getStatus();
        int actually = clientErrorExeptionHandler.toResponse(new ClientErrorException(Response.Status.BAD_REQUEST)).getStatus();
        Assert.assertEquals(expected, actually);
    }
}
