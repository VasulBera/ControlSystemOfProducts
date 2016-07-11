package com.softserveinc.trainee.service;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
        Entity entity = entityDao.getEntity(id);
        if(entity == null){
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        return entity;
    }

   /* @GET
    public List<Entity> getAllEntities(){
        return entityDao.getAllEntity();
    }*/

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
