package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.metadata.PreviousStateEntity;

public interface PreviousStateEntityDao {

    public PreviousStateEntity getEntity(String id);

    public void addEntity(PreviousStateEntity entity);

    public PreviousStateEntity updateEntity(PreviousStateEntity entity);


}
