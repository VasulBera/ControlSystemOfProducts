package com.softserveinc.trainee.generator;


import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.metadata.Entity;

public interface TableGenerator {

    public void createTable(Entity entity);

    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity);

    public void deleteTable(String tableName);
}
