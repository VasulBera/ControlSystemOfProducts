package com.softserveinc.trainee.service.Impl;

import com.softserveinc.trainee.applicationConfig.ApplicationContextProvider;
import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.executer.JobExecutor;
import com.softserveinc.trainee.service.JobService;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JobServiceImpl implements JobService {

    @Autowired
    ApplicationContextProvider applicationContextProvider;

    @Override
    public void createTask(@Suspended AsyncResponse response, RequestJob requestJob){
        new Thread(){
            public void run(){
                JobExecutor jobExecutor = applicationContextProvider.getApplicationContext().getBean(requestJob.getDescription(), JobExecutor.class);
                jobExecutor.execute(requestJob);
            }
        }.start();
        Response acceptedResponse = Response.status(Response.Status.ACCEPTED).build();
        response.resume(acceptedResponse);
    }
}
