package com.softserveinc.trainee.sender;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public interface RequestHttpMethod {

    public HttpGet getHttpGet(String json);
    public HttpPost getHttpPost(String owner);
}
