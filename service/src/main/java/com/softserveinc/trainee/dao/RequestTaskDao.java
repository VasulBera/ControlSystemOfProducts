package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.Entity;

/**
 * Created by vberv on 8/15/2016.
 */
public interface RequestTaskDao {

    public void createRequestTask(String id, String description, String owner);

    public void createEntityTable(Entity entity);
}
