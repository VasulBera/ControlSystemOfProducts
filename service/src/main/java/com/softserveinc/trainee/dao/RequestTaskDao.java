package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.Entity;

public interface RequestTaskDao {

    public void createRequestTask(String id, String description, String owner);

    public void createEntityTable(Entity entity);
}
