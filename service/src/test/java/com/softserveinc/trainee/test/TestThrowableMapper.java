package com.softserveinc.trainee.test;

import com.softserveinc.trainee.mapper.ClientErrorExeptionHandler;
import com.softserveinc.trainee.mapper.ThrowableMapper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Created by vberv on 7/20/2016.
 */
public class TestThrowableMapper {

    private static ThrowableMapper throwableMapper;

    @BeforeClass
    public static void initialize(){
        throwableMapper  = new ThrowableMapper();
    }

    @Test
    public void testToResponse(){
        int expected = Response.status(Response.Status.BAD_REQUEST).build().getStatus();
        int actually = throwableMapper.toResponse(new Throwable()).getStatus();
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void delete(){
        throwableMapper = null;
    }
}
