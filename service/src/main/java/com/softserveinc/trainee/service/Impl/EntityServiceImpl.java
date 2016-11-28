package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.applicationConfig.FieldComparator;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.entity.metadata.*;
import com.softserveinc.trainee.entity.administration.*;
import com.softserveinc.trainee.service.EntityService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EntityServiceImpl implements EntityService {

    @Autowired
    EntityDao entityDao;
	
	@Autowired
	PreviousStateEntityDao previousStateEntityDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityServiceImpl.class);

    private static final String REPLACE_REGEX = "[^a-zA-Z0-9\\_]";

    @Override
    public Entity getEntity(String id) {
        return entityDao.getEntity(id);
    }

    @Override
    public List<Entity> getAllEntities(){
        return entityDao.getAllEntities();
    }

    @Override
    public Entity addEntity(Entity entity) {
        if(entity.getId() == null) {
            entity.setId((entity.getSchemaName() + entity.getTableName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
        }
        if(entity.getFieldList() != null) {
            for(Field field: entity.getFieldList()){
                field.setId((entity.getId() + field.getColumnName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
            }
        }
        Entity addedEntity = entityDao.addEntity(entity);
        if(!(addedEntity.getFieldList() == null)){
            Collections.sort(addedEntity.getFieldList(), new FieldComparator());
        }
        return addedEntity;
    }
    @Override
    public Entity patchEntity(String id, Entity enteredEntity) {
        Entity entity = entityDao.getEntity(id);
        if (entity == null) {
            LOGGER.warn("Method: getAllEntities(); No entity for patching");
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
    @Override
    public Entity updateEntity(Entity entity){
        if (entity.getId() == null){
            entity.setId((entity.getSchemaName() + entity.getTableName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
        }
        if(entity.getFieldList() != null) {
            for(Field field: entity.getFieldList()){
                if(field.getId() == null) {
                    field.setId((entity.getId() + field.getColumnName()).replaceAll(REPLACE_REGEX, "").toUpperCase());
                }
            }
        }
        Entity updatedEntity = entityDao.updateEntity(entity);
        if(!updatedEntity.getFieldList().isEmpty()){
            Collections.sort(updatedEntity.getFieldList(), new FieldComparator());
        }
        return updatedEntity;
    }

    public void deleteEntity(String id){
        entityDao.deleteEntity(id);
		previousStateEntityDao.deleteEntity(id);
		
    }


    @Override
    public List<Entity> getEntityWithSchemaNameClient() {
        return entityDao.getEntitiesWithShemaNameClient();
    }
}
