package com.softserveinc.trainee.sender.Impl.salesforce;

import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.sender.RequestHttpMethod;
import com.softserveinc.trainee.sender.RequestSender;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SalesForceRequestSender implements RequestSender{

    @Autowired
    RequestHttpMethod requestHttpMethod;

    private HttpClient client = HttpClients.createDefault();
    private BasicResponseHandler responseHandler = new BasicResponseHandler();

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesForceRequestSender.class);

    public void sendRequest(RequestJob requestJob) {
        HttpPost httpPost = requestHttpMethod.getHttpPost(requestJob.getOwner());
        HttpResponse response = null;
        try {
            response = client.execute(httpPost);
            String json = responseHandler.handleResponse(response);
            HttpGet httpGet = requestHttpMethod.getHttpGet(json);
            httpGet.addHeader("execution-status", requestJob.getStatus().name());
            client.execute(httpGet);
        } catch (IOException e) {
            LOGGER.warn("Method: sendRequest(RequestJob requestJob); Couldn't send request to salesforce organization");
            e.printStackTrace();
        }
    }
}
