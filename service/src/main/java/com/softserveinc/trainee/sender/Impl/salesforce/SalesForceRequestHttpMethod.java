package com.softserveinc.trainee.sender.Impl.salesforce;

import com.google.gson.Gson;
import com.softserveinc.trainee.sender.RequestHttpMethod;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component
public class SalesForceRequestHttpMethod implements RequestHttpMethod {

    @Resource(name="salesforceConfig")
    private Properties properties;

    private Gson gson = new Gson();

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesForceRequestHttpMethod.class);

    @Override
    public HttpGet getHttpGet(String json) {
        Map<String, String> result = gson.fromJson(json, HashMap.class);
        String accessToken = result.get(SalesForceProperties.ACCESS_TOKEN_KEY.getValue());
        String instanceUrl = result.get(SalesForceProperties.INSTANCE_URL_KEY.getValue());
        String pathToGetMethod = instanceUrl + SalesForceProperties.GET_METHOD_LINK.getValue();
        HttpGet httpGet = new HttpGet(pathToGetMethod);
        httpGet.addHeader(SalesForceProperties.AUTHORIZATION_KEY.getValue(), SalesForceProperties.AUTHORIZATION_CODE_PREFIX.getValue() + accessToken);
        return httpGet;
    }

    @Override
    public HttpPost getHttpPost(String owner) {
        String clientId = properties.getProperty(SalesForceProperties.CLIENT_ID_PREFIX.getValue() + "." + owner);
        String clientSecret = properties.getProperty(SalesForceProperties.CLIENT_SECRET_PREFIX.getValue() + "." + owner);
        String username = properties.getProperty(SalesForceProperties.USERNAME.getValue() + "." + owner);
        String password = properties.getProperty(SalesForceProperties.PASSWORD.getValue() + "." + owner);
        String securityToken = properties.getProperty(SalesForceProperties.SECURITY_TOKEN.getValue() + "." + owner);

        HttpPost httpPost = new HttpPost(SalesForceProperties.URL.getValue());
        List<NameValuePair> parameters = new ArrayList();

        parameters.add(new BasicNameValuePair("client_id", clientId));
        parameters.add(new BasicNameValuePair("client_secret", clientSecret));
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password + securityToken));
        parameters.add(new BasicNameValuePair("grant_type", "password"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Method: getHttpPost(String owner); Couldn't create HttpPost");
            e.printStackTrace();
        }
        return httpPost;
    }
}
