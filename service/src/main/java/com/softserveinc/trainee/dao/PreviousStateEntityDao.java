package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import org.springframework.transaction.annotation.Transactional;

public interface PreviousStateEntityDao {

    @Transactional("transactionManagerAdministration")
    public PreviousStateEntity getPreviousStateEntity(String id);

    @Transactional("transactionManagerAdministration")
    public boolean addPreviousStateEntity(PreviousStateEntity entity);

    @Transactional("transactionManagerAdministration")
    public PreviousStateEntity updatePreviousStateEntity(PreviousStateEntity entity);


}
