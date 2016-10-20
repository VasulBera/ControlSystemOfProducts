package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.administration.RequestJob;
import com.softserveinc.trainee.entity.administration.RequestJobStatus;
import com.softserveinc.trainee.sender.Impl.salesforce.SalesForceRequestSender;
import com.softserveinc.trainee.sender.RequestHttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Matchers.any;

public class TestSalesForceRequestSender {

    @Mock
    private RequestHttpMethod requestHttpMethod;

    @Mock
    private HttpClient client;

    @Mock
    private BasicResponseHandler responseHandler;

    @InjectMocks
    private SalesForceRequestSender requestSender = new SalesForceRequestSender();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

   // @Test
    public void testSendRequest() throws IOException {
        RequestJob requestJob = new RequestJob();
        requestJob.setAim("All_entities");
        requestJob.setOwner("Mikle");
        requestJob.setStatus(RequestJobStatus.DONE);
        HttpPost httpPost = new HttpPost();
        HttpGet httpGet = new HttpGet();
        String json = "{\"access_token\":\"accesstoken\",\"instance_url\":\"instanceurl\"}";
        Mockito.when(requestHttpMethod.getHttpPost("Mikle")).thenReturn(httpPost);
        Mockito.when(responseHandler.handleResponse(any(HttpResponse.class))).thenReturn(json);
        Mockito.when(requestHttpMethod.getHttpGet(json)).thenReturn(httpGet);
        Mockito.when(client.execute(httpGet)).thenReturn(null);
        requestSender.sendRequest(requestJob);
    }
}
