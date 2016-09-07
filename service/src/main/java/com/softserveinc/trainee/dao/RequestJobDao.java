package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import org.springframework.transaction.annotation.Transactional;

public interface RequestJobDao {

    @Transactional("transactionManagerAdministration")
    public void createRequestTask(RequestJob requestJob);

    @Transactional("transactionManagerAdministration")
    public RequestJob updateRequestTask(RequestJob requestJob);

    /*public void createEntityTable(Entity entity);

    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity);*/
}
