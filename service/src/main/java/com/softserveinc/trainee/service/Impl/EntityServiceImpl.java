package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.service.EntityService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EntityServiceImpl implements EntityService {

    @Autowired
    EntityDao entityDao;

    private static final String REPLACE_REGEX = "[^a-zA-Z0-9\\_]";

    public Entity getEntity(String id) {
        Entity entity = entityDao.getEntity(id);
        if(entity == null){
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
        return entity;
    }

    public List<Entity> getAllEntities(){
        List<Entity> listEntities = entityDao.getAllEntity();
        if(listEntities.size() == 0) {
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        } else {
            return listEntities;
        }
    }

    public Entity addEntity(Entity entity) {
        if(entity.getId() == null) {
            entity.setId((entity.getSchemaName() + entity.getTableName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
        }
        if(entity.getFieldList() != null) {
            for(Field field: entity.getFieldList()){
                field.setId((entity.getId() + field.getName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
            }
        }
        return entityDao.addEntity(entity);
    }

    public Entity patchEntity(String id, Entity enteredEntity) {
        Entity entity = entityDao.getEntity(id);
        if (entity == null) {
            throw new NotFoundException();
        }
        if(enteredEntity.getName() != null){
            entity.setName(enteredEntity.getName());
        }
        if(enteredEntity.getSchemaName() != null){
            entity.setSchemaName(enteredEntity.getSchemaName());
        }
        if(enteredEntity.getTableName() != null){
            entity.setTableName(enteredEntity.getTableName());
        }
        if(enteredEntity.getFieldList() != null) {
            for (Field enteredEntityField : enteredEntity.getFieldList()) {
                for (Field fieldDb : entity.getFieldList()) {
                    if (fieldDb.getId().equals(enteredEntityField.getId())) {
                        fieldDb.setId(enteredEntityField.getId());
                        fieldDb.setColumnName(enteredEntityField.getColumnName());
                        fieldDb.setName(enteredEntityField.getName());
                        fieldDb.setLength(enteredEntityField.getLength());
                        fieldDb.setType(enteredEntityField.getType());
                    }
                }
            }
        }
        return entityDao.updateEntity(entity);
    }

    public Entity updateEntity(Entity entity){
        if (entity.getId() == null){
            entity.setId((entity.getSchemaName() + entity.getTableName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
        }
        return entityDao.updateEntity(entity);
    }

    public void deleteEntity(String id){
        Entity entity = entityDao.getEntity(id);
        if(entity != null){
            entityDao.deleteEntity(entity);
        }else {
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }
    }
}
