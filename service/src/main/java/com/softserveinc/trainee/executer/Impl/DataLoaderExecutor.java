package com.softserveinc.trainee.executer.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.JobExecutor;
import com.softserveinc.trainee.loader.DataLoader;
import com.softserveinc.trainee.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.List;

public class DataLoaderExecutor implements JobExecutor {

    @Autowired
    RequestJobDao requestTaskDao;

    @Autowired
    EntityDao entityDao;

    @Autowired
    RequestSender requestSender;

    @Autowired
    DataLoader dataLoader;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLoaderExecutor.class);

    @Override
    public void execute(RequestJob requestJob) {
        requestTaskDao.createRequestTask(requestJob);
        List<Entity> entitiesList = entityDao.getEntitiesWithShemaNameClient();
        try {
            for(Entity entity: entitiesList) {
                dataLoader.load(entity);
            }
            requestJob.setStatus(RequestJobStatus.DONE);
        }catch (IOException e) {
            LOGGER.warn("Method: execute(RequestJob requestJob); Couldn't  load data \n " + e.toString());
            requestJob.setStatus(RequestJobStatus.ERROR);
        }finally {
            requestTaskDao.updateRequestTask(requestJob);
        }
		System.out.println("Before");
        requestSender.sendRequest(requestJob);
		System.out.println("After");
    }
}
