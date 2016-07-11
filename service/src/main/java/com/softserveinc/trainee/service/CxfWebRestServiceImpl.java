package com.softserveinc.trainee.service;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Path("/entity")
@Produces("application/json")
@Transactional
public class CxfWebRestServiceImpl {

    @Autowired
    EntityDao entityDao;

    @GET
    @Path("/{id}")
    public Entity getEntity(@PathParam("id") String id) {
        return entityDao.getEntity(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Entity addEntity(Entity entity) {
        return entityDao.addEntity(entity);
    }

    @PUT
    @Produces("application/json")
    public Entity updateEntity(Entity entity){
       return entityDao.updateEntity(entity);
    }

    @DELETE
    @Path("/{id}")
    public void deleteEntity(@PathParam("id") String id){
        entityDao.deleteEntity(id);
    }
}
