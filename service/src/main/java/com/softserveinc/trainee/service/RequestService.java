package com.softserveinc.trainee.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;

@Service
@Path("/task")
@Consumes("application/json")
@Produces("application/json")
@Transactional
public interface RequestService {

    @GET
    @Path("/{id : .+}/{description}/{owner}")
    public void createTask(@PathParam("id") String id, @PathParam("description") String description, @PathParam("owner") String owner);

    @GET
    @Path("/create/{id : .+}")
    public void createEntity(@PathParam("id") String id);

}
