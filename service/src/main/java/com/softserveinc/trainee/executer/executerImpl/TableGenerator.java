package com.softserveinc.trainee.executer.executerImpl;

import com.softserveinc.trainee.applicationUtil.SalesForceConnection;
import com.softserveinc.trainee.dao.*;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.JobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class TableGenerator implements JobExecutor {

    @Autowired
    RequestJobDao requestJobDao;

    @Autowired
    EntityDao entityDao;

    @Autowired
    PreviousStateEntityDao previousStateEntityDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomTableDao customTableDao;

    SalesForceConnection salesForceConnection = SalesForceConnection.getInstance();

    @Override
    public void execute(RequestJob requestJob) {
        requestJobDao.createRequestTask(requestJob);
        List<Entity> entitiesList = entityDao.getAllEntity();
        for(Entity entity: entitiesList)
        {
            boolean isRegistered = previousStateEntityDao.addPreviousStateEntity(entity.createPreviousStateEntity());
            if(!isRegistered)
            {
                PreviousStateEntity previousStateEntityDB = previousStateEntityDao.getPreviousStateEntity(entity.getId());
                customTableDao.updateTable(previousStateEntityDB, entity);
                previousStateEntityDao.updatePreviousStateEntity(entity.createPreviousStateEntity());
            }else
            {
                customTableDao.generateTable(entity);
            }
        }
        requestJob.setStatus(RequestJobStatus.DONE);
        requestJobDao.updateRequestTask(requestJob);
        //User user = userDao.getUserByUsername(requestJob.getDescription());
        salesForceConnection.sendRequest(requestJob.getOwner());
    }
}
