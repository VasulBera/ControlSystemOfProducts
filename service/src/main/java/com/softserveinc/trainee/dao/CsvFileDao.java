package com.softserveinc.trainee.dao;


import com.softserveinc.trainee.entity.metadata.Entity;

public interface CsvFileDao {
    //public void uploadData(String name);

    public void uploadData(Entity entity);
}
