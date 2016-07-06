package com.softserveinc.trainee.service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class HandleClientErrorExeption implements ExceptionMapper<ClientErrorException> {

    @Override
    public Response toResponse(ClientErrorException exception) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
