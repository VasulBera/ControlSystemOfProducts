package com.softserveinc.trainee.applicationUtil;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class SalesForceConnection {



    static SalesForceConnection salesForceConnection;

    private static final String PATH_TO_SALESFORCE_PROPERTIES = "service\\src\\main\\resources\\salesforce.properties";
    private static String CLIENT_ID_PREFIX = "client_id";
    private static String CLIENT_SECRET_PREFIX = "client_secret";
    private static String USERNAME = "username";
    private static String PASSWORD = "password";
    private static String SECURITYTOKEN = "securityToken";
    private static final String URL = "https://login.salesforce.com/services/oauth2/token";
    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String INSTANCE_URL_KEY = "instance_url";
    private static final String GET_METHOD_LINK = "/services/apexrest/task";
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String AUTHORIZATION_CODE_PREFIX = "OAuth ";

    private SalesForceConnection(){

    }

    public static SalesForceConnection getInstance(){
        if(salesForceConnection == null){
            salesForceConnection = new SalesForceConnection();
        }
        return salesForceConnection;
    }

   public void sendRequest(String name){

       Properties properties = new Properties();
       try(InputStream inputStream = new FileInputStream(PATH_TO_SALESFORCE_PROPERTIES)){
           properties.load(inputStream);
       } catch (IOException e) {
           System.out.println("Cannot load properties file");
       }
       String clientId = properties.getProperty(CLIENT_ID_PREFIX + "." + name);
       String clientSecret = properties.getProperty(CLIENT_SECRET_PREFIX + "." + name);
       String username = properties.getProperty(USERNAME + "." + name);
       String password = properties.getProperty(PASSWORD + "." + name);
       String securityToken = properties.getProperty(SECURITYTOKEN + "." + name);


       HttpClient client = HttpClients.createDefault();
       HttpPost httpPost = new HttpPost(URL);
       List<NameValuePair> parameters = new ArrayList();
       parameters.add(new BasicNameValuePair("client_id", clientId));
       parameters.add(new BasicNameValuePair("client_secret", clientSecret));
       parameters.add(new BasicNameValuePair("username", username));
       parameters.add(new BasicNameValuePair("password", password + securityToken));
       parameters.add(new BasicNameValuePair("grant_type", "password"));

         try
       {
           httpPost.setEntity(new UrlEncodedFormEntity(parameters));
       }
         catch (UnsupportedEncodingException e)
       {
           System.out.println("SalesForceRequest.sendRequest(): UnsupportedEncodingException ");
       }

       HttpResponse response = null;
         try
       {
           response = client.execute(httpPost);
       }
         catch (IOException e)
       {
           System.out.println("SalesForceRequest.sendRequest(): IOException: cannot execute POST method ");
       }

       String json = null;
         try
       {
           json = new BasicResponseHandler().handleResponse(response);
       }
         catch (IOException e)
       {
           System.out.println("SalesForceRequest.sendRequest(): IOException: cannot create json ");
       }
       Map<String, String> result = new Gson().fromJson(json, HashMap.class);
       String accessToken = result.get(ACCESS_TOKEN_KEY);
       String instanceUrl = result.get(INSTANCE_URL_KEY);

       String pathToGetMethod = instanceUrl + GET_METHOD_LINK;

       HttpGet httpGet = new HttpGet(pathToGetMethod);
       httpGet.addHeader(AUTHORIZATION_KEY, AUTHORIZATION_CODE_PREFIX + accessToken);

         try
       {
          client.execute(httpGet);
       }
         catch (IOException e)
       {
           System.out.println("SalesForceRequest.sendRequest(): IOException: cannot execute GET method ");
       }
   }
}
