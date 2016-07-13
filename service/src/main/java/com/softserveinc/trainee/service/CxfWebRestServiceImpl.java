package com.softserveinc.trainee.service;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/entity")
@Consumes("application/json")
@Produces("application/json")
@Transactional
public class CxfWebRestServiceImpl {

    @Autowired
    EntityDao entityDao;

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";
    private static final String REPLACE_REGEX = "[^a-zA-Z0-9]";
    private static final Integer MAX_LENGTH_VALUE = 128;
    private static final Integer MIN_LENGTH_VALUE = 0;

    @GET
    @Path("/{id : .+}")
    public Entity getEntity(@PathParam("id") String id) {
        Entity entity = entityDao.getEntity(id);
        if(entity == null){
            throw new NotFoundException();
        }
        return entity;
    }

    @GET
    public List<Entity> getAllEntities(){
        return entityDao.getAllEntity();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Entity addEntity(Entity entity) {
        if (entity.getSchemaName() == null
                || entity.getTableName() == null
                || !entity.getSchemaName().matches(VALIDATE_REGEX)
                || !entity.getTableName().matches(VALIDATE_REGEX)
                || entity.getSchemaName().length() > MAX_LENGTH_VALUE
                || entity.getTableName().length() > MAX_LENGTH_VALUE) {
            throw new NotFoundException();
        }
        entity.setId((entity.getSchemaName() + entity.getTableName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
        if (entityDao.getEntity(entity.getId()) != null) {
            throw new NotFoundException();
        }
        if(entity.getFieldList() != null) {
            for (Field field : entity.getFieldList()) {
                if(field.getName() == null
                        || field.getType() == null
                        || !field.getName().matches(VALIDATE_REGEX)
                        || field.getName().length() > MAX_LENGTH_VALUE
                        || field.getLength() < MIN_LENGTH_VALUE) {
                    throw new NotFoundException();
                }
                field.setId(entity.getId() + field.getName().replaceAll(REPLACE_REGEX, "").toUpperCase());
            }
        }
        return entityDao.addEntity(entity);
    }

    @PUT
    @Produces("application/json")
    public Entity updateEntity(Entity entity){
        if (entity.getId() == null
                || entity.getSchemaName() == null
                || entity.getTableName() == null
                || !entity.getSchemaName().matches(VALIDATE_REGEX)
                || !entity.getTableName().matches(VALIDATE_REGEX)
                || entity.getSchemaName().length() > MAX_LENGTH_VALUE
                || entity.getTableName().length() > MAX_LENGTH_VALUE) {
            throw new NotFoundException();
        }
        if(entity.getFieldList() != null) {
            for (Field field : entity.getFieldList()) {
                if(field.getId() == null
                        || field.getName() == null
                        || field.getType() == null
                        || !field.getName().matches(VALIDATE_REGEX)
                        || field.getName().length() > MAX_LENGTH_VALUE
                        || field.getLength() < MIN_LENGTH_VALUE) {
                    throw new NotFoundException();
                }
            }
        }
        Entity entityDb = entityDao.getEntity(entity.getId());
        if(entityDb != null) {
            return entityDao.updateEntity(entity);
        }else {
            if(entity.getFieldList() != null) {
                for (Field field : entity.getFieldList()) {
                    field.setId(entity.getId() + field.getName().replaceAll(REPLACE_REGEX, "").toUpperCase());
                }
            }
            return entityDao.addEntity(entity);
        }
    }

    @DELETE
    @Path("/{id : .+}")
    public void deleteEntity(@PathParam("id") String id){
        Entity entity = entityDao.getEntity(id);
        if(entity != null){
            entityDao.deleteEntity(entity);
        }else {
            throw new NotFoundException();
        }

    }
}
