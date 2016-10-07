package com.softserveinc.trainee.sender;

import com.softserveinc.trainee.entity.administration.RequestJob;

public interface RequestSender {

    public void sendRequest(RequestJob requestJob);
}
