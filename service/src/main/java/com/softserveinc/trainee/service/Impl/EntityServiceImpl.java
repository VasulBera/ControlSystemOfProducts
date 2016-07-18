package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

public class EntityServiceImpl implements EntityService {

    @Autowired
    EntityDao entityDao;

    private static final String VALIDATE_REGEX = "[a-zA-Z0-9\\_]+";
    private static final String REPLACE_REGEX = "[^a-zA-Z0-9]";
    private static final Integer MAX_LENGTH_VALUE = 128;
    private static final Integer MIN_LENGTH_VALUE = 0;

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

    public Entity patchEntity(String id, Entity enteredEntity){
        Entity entityDb = entityDao.getEntity(id);
        if (entityDb == null) {
            throw new NotFoundException();
        }else {
            if(enteredEntity.getSchemaName() != null) {
                if(enteredEntity.getSchemaName().matches(VALIDATE_REGEX)) {
                    entityDb.setSchemaName(enteredEntity.getSchemaName());
                }else {
                    throw new NotFoundException();
                }
            }
            if(enteredEntity.getTableName() != null) {
                if(enteredEntity.getTableName().matches(VALIDATE_REGEX)){
                    entityDb.setTableName(enteredEntity.getTableName());
                } else {
                    throw new NotFoundException();
                }
            }
            if(enteredEntity.getFieldList() != null) {
                for(Field enteredEntityField: enteredEntity.getFieldList()) {
                    for(Field fieldDb: entityDb.getFieldList()){
                        if(enteredEntityField.getId() == null
                                ||enteredEntityField.getName() == null
                                || enteredEntityField.getType() == null
                                || !enteredEntityField.getName().matches(VALIDATE_REGEX)
                                || enteredEntityField.getName().length() > MAX_LENGTH_VALUE
                                || enteredEntityField.getLength() < MIN_LENGTH_VALUE) {
                            throw new NotFoundException();
                        } else {
                            if(fieldDb.getId().equals(enteredEntityField.getId())){
                                fieldDb.setId(enteredEntityField.getId());
                                fieldDb.setName(enteredEntityField.getName());
                                fieldDb.setLength(enteredEntityField.getLength());
                                fieldDb.setType(enteredEntityField.getType());
                            }
                        }
                    }
                }

            }
            return entityDao.updateEntity(entityDb);
        }
    }

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

    public void deleteEntity(String id){
        Entity entity = entityDao.getEntity(id);
        if(entity != null){
            entityDao.deleteEntity(entity);
        }else {
            throw new ClientErrorException(Response.Status.NOT_FOUND);
        }

    }
}
