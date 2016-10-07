package com.softserveinc.trainee.executer.Impl;

import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.PreviousStateEntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.JobExecutor;
import com.softserveinc.trainee.generator.TableGenerator;
import com.softserveinc.trainee.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TableGeneratorExecutor implements JobExecutor {

    @Autowired
    RequestJobDao requestJobDao;

    @Autowired
    EntityDao entityDao;

    @Autowired
    PreviousStateEntityDao previousStateEntityDao;

    @Autowired
    TableGenerator tableGenerator;

    @Autowired
    RequestSender requestSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(TableGeneratorExecutor.class);

    @Override
    public void execute(RequestJob requestJob) {
        requestJobDao.createRequestTask(requestJob);
        List<Entity> entitiesList = entityDao.getAllEntities();
        for(Entity entity: entitiesList) {
            if(previousStateEntityDao.addPreviousStateEntity(entity.createPreviousStateEntity())) {
                PreviousStateEntity previousStateEntityDB = previousStateEntityDao.getPreviousStateEntity(entity.getId());
                tableGenerator.updateTable(previousStateEntityDB, entity);
                previousStateEntityDao.updatePreviousStateEntity(entity.createPreviousStateEntity());
            }else {
                tableGenerator.createTable(entity);
            }
        }
        requestJob.setStatus(RequestJobStatus.DONE);
        requestJobDao.updateRequestTask(requestJob);
        requestSender.sendRequest(requestJob);
    }
}
