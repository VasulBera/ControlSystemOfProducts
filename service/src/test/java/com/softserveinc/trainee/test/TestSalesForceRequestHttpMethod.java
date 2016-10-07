package com.softserveinc.trainee.test;

import com.softserveinc.trainee.sender.Impl.salesforce.SalesForceProperties;
import com.softserveinc.trainee.sender.Impl.salesforce.SalesForceRequestHttpMethod;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class TestSalesForceRequestHttpMethod {

    @Mock
    private Properties properties;

    @InjectMocks
    SalesForceRequestHttpMethod salesForceRequestHttpMethod = new SalesForceRequestHttpMethod();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetHttpGet(){
        String json = "{\"access_token\":\"accesstoken\",\"instance_url\":\"instanceurl\"}";
        HashMap<String, String> result = new HashMap<>();
        result.put(SalesForceProperties.ACCESS_TOKEN_KEY.getValue(), "accesstoken");
        result.put(SalesForceProperties.INSTANCE_URL_KEY.getValue(), "instanceurl");

        HttpGet expected = new HttpGet("instanceurl/services/apexrest/task");
        expected.addHeader(SalesForceProperties.AUTHORIZATION_KEY.getValue(), SalesForceProperties.AUTHORIZATION_CODE_PREFIX.getValue() + "accesstoken");

        HttpGet actually = salesForceRequestHttpMethod.getHttpGet(json);
        Assert.assertEquals(Arrays.toString(expected.getAllHeaders()), Arrays.toString(actually.getAllHeaders()));
        Assert.assertEquals(expected.getURI(), actually.getURI());
    }

    @Test
    public void testGetHttpPost() throws UnsupportedEncodingException {
        String ownerName = "Mike";

        Mockito.when(properties.getProperty(SalesForceProperties.CLIENT_ID_PREFIX.getValue() + "." + ownerName)).thenReturn("123");
        Mockito.when(properties.getProperty(SalesForceProperties.CLIENT_SECRET_PREFIX.getValue() + "." + ownerName)).thenReturn("clientsecret");
        Mockito.when(properties.getProperty(SalesForceProperties.USERNAME.getValue() + "." + ownerName)).thenReturn("username");
        Mockito.when(properties.getProperty(SalesForceProperties.PASSWORD.getValue() + "." + ownerName)).thenReturn("password");
        Mockito.when(properties.getProperty(SalesForceProperties.SECURITY_TOKEN.getValue() + "." + ownerName)).thenReturn("securitytoken");

        HttpPost expected = new HttpPost(SalesForceProperties.URL.getValue());
        List<NameValuePair> parameters = new ArrayList();

        parameters.add(new BasicNameValuePair("client_id", "123"));
        parameters.add(new BasicNameValuePair("client_secret", "clientsecret"));
        parameters.add(new BasicNameValuePair("username", "username"));
        parameters.add(new BasicNameValuePair("password", "password" + "securitytoken"));
        parameters.add(new BasicNameValuePair("grant_type", "password"));

        expected.setEntity(new UrlEncodedFormEntity(parameters));

        HttpPost actually = salesForceRequestHttpMethod.getHttpPost(ownerName);
        Assert.assertEquals(expected.getURI(), actually.getURI());
        Assert.assertEquals(expected.getEntity().toString(), actually.getEntity().toString());
    }
}
