package com.softserveinc.trainee.executer.executerImpl;


import com.softserveinc.trainee.applicationUtil.SalesForceConnection;
import com.softserveinc.trainee.dao.CsvFileDao;
import com.softserveinc.trainee.dao.Impl.CustomTableDaoImpl;
import com.softserveinc.trainee.dao.UserDao;
import com.softserveinc.trainee.dao.EntityDao;
import com.softserveinc.trainee.dao.RequestJobDao;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.executer.JobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CsvLoader implements JobExecutor {

    @Autowired
    RequestJobDao requestTaskDao;

    @Autowired
    EntityDao entityDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CustomTableDaoImpl customTableDaoImpl;

    @Autowired
    CsvFileDao csvFileDao;

    SalesForceConnection salesForceConnection = SalesForceConnection.getInstance();

    @Override
    public void execute(RequestJob requestJob) {
        requestTaskDao.createRequestTask(requestJob);
        List<Entity> entitiesList = entityDao.getAllEntity();
        for(Entity entity: entitiesList)
        {
            if(entity.getSchemaName().equals("client"))
            {
                if(entity.isFullUploadData())
                {
                    customTableDaoImpl.deleteAllRecord(entity);
                }
                csvFileDao.uploadData(entity);
            }

        }
        requestJob.setStatus(RequestJobStatus.DONE);
        requestTaskDao.updateRequestTask(requestJob);
        //User user = userDao.getUserByUsername(requestJob.getDescription());
        salesForceConnection.sendRequest(requestJob.getOwner());
    }
}
