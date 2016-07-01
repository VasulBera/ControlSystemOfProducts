package com.softserveinc.trainee.service;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Service
@Path("/entity")
@Produces("application/json")
@Transactional
public class CxfWebRestServiceImpl {

    private static final String CHECK_IS_THIS_NUMBER = "\\d+";

    @Autowired
    EntityDao entityDao;

    @GET
    @Path("/{id}")
    public Response getEntity(@PathParam("id") String id) {
        if(!id.matches(CHECK_IS_THIS_NUMBER)){
            return  Response.status(Response.Status.BAD_REQUEST).build();
        }else {
            Integer entityId = Integer.parseInt(id);
            Entity entity = entityDao.getEntity(entityId);
            if (entity == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                return Response.ok(entity).build();
            }
        }
    }
}
