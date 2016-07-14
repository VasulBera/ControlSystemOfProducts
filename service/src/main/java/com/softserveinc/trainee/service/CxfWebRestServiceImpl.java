package com.softserveinc.trainee.service;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import org.apache.cxf.jaxrs.ext.PATCH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @GET
    public List<Entity> getAllEntities(){
        List<Entity> listEntities = entityDao.getAllEntity();
        if(listEntities.size() == 0) {
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        } else {
            return listEntities;
        }
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

    @PATCH
    @Path("/{id : .+}")
    @Produces("application/json")
    public Entity patchEntity(@PathParam("id")String id, Entity entity){
        Entity entityDb = entityDao.getEntity(id);
        if (entityDb == null) {
            throw new NotFoundException();
        }else {
            if(entity.getSchemaName() != null) {
                if(entity.getSchemaName().matches(VALIDATE_REGEX)) {
                    entityDb.setSchemaName(entity.getSchemaName());
                }else {
                    throw new NotFoundException();
                }
            }
            if(entity.getTableName() != null) {
                if(entity.getTableName().matches(VALIDATE_REGEX)){
                    entityDb.setTableName(entity.getTableName());
                } else {
                    throw new NotFoundException();
                }
            }
           /* if(entity.getFieldList() != null) {
                for(Field field: entity.getFieldList()) {
                    for(Field fieldDb: entityDb.getFieldList()){
                        if(field.getId() == fieldDb.getId()){
                            if(field.getName() == null
                                    || field.getType() == null
                                    || !field.getName().matches(VALIDATE_REGEX)
                                    || field.getName().length() > MAX_LENGTH_VALUE
                                    || field.getLength() < MIN_LENGTH_VALUE) {
                                throw new NotFoundException();
                            }
                        }
                    }
                }
                entityDb.setFieldList(entity.getFieldList());
            }*/
            return entityDao.updateEntity(entityDb);
        }
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
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }

    }
}
