package com.softserveinc.trainee.service;

import com.softserveinc.trainee.entity.administration.RequestJob;
import org.springframework.stereotype.Service;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Service
@Path("/task")
@Consumes("application/json")
@Produces("application/json")
public interface JobService {

    @POST
    @Path("/")
    public void createTask(@Suspended final AsyncResponse response, RequestJob requestJob);

}
