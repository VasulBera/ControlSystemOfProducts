package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.administration.RequestTask;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.PreviousStateEntity;

public interface RequestTaskDao {

    public void createRequestTask(RequestTask requestTask);

    public void createEntityTable(Entity entity);

    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity);
}
