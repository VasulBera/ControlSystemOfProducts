package com.softserveinc.trainee.dao;

import com.softserveinc.trainee.entity.administration.RequestJob;
import org.springframework.transaction.annotation.Transactional;

public interface RequestJobDao {

    @Transactional("transactionManagerAdministration")
    public void createRequestTask(RequestJob requestJob);

    @Transactional("transactionManagerAdministration")
    public RequestJob updateRequestTask(RequestJob requestJob);
}
