package com.softserveinc.trainee.service;

import com.softserveinc.trainee.entity.metadata.Entity;
import org.apache.cxf.jaxrs.ext.PATCH;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/entity")
@Consumes("application/json")
@Produces("application/json")
@Transactional("transactionManager")
public interface EntityService {

    @GET
    @Path("/{id : .+}")
    public Entity getEntity(@PathParam("id") String id);

    @GET
    public List<Entity> getAllEntities();

    @POST
    public Entity addEntity(Entity entity);

    @PATCH
    @Path("/{id : .+}")
    public Entity patchEntity(@PathParam("id")String id, Entity entity) throws Throwable;

    @PUT
    public Entity updateEntity(Entity entity);

    @DELETE
    @Path("/{id : .+}")
    public void deleteEntity(@PathParam("id") String id);

}
