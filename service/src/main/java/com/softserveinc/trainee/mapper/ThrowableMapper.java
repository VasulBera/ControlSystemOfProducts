package com.softserveinc.trainee.mapper;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
