package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.metadata.Entity;

public interface CustomTableDao {

    public void generateTable(Entity entity);

    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity);

    public void deleteAllRecord(Entity entity);

}
