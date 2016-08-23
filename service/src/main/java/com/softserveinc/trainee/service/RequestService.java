package com.softserveinc.trainee.service;

import com.softserveinc.trainee.entity.administration.RequestTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;

@Service
@Path("/task")
@Consumes("application/json")
@Produces("application/json")
@Transactional("transactionManagerAdministration")
public interface RequestService {

    @POST
    @Path("/")
    public void createTask(RequestTask requestTask);

    @GET
    @Path("/create/{id : .+}")
    public void createTables(@PathParam("id") String id);

    @GET
    @Path("/update/{id : .+}")
    public void updateTable(@PathParam("id") String id);

}
