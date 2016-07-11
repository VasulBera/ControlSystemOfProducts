package com.softserveinc.trainee.mapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WebApplicationExceptionHandler implements ExceptionMapper<WebApplicationException>{

    @Override
    public Response toResponse(WebApplicationException exception) {
        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
    }
}
