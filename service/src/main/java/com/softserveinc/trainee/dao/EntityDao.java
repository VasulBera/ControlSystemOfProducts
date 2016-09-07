package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.metadata.Entity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityDao {

    @Transactional("transactionManager")
    public Entity getEntity(String id);

    @Transactional("transactionManager")
    public Entity addEntity(Entity entity);

    @Transactional("transactionManager")
    public Entity updateEntity(Entity entity);

    @Transactional("transactionManager")
    public void deleteEntity(String id);

    @Transactional("transactionManager")
    public List<Entity> getAllEntity();
}
