package com.softserveinc.trainee.loader.Impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.softserveinc.trainee.loader.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class AmazonS3DataSource implements DataSource {

    @Value("${bucketName}")
    private String bucketName;

    @Value("${AWSAccessKeyId}")
    private String accessKey;

    @Value("${AWSSecretKey}")
    private String secretKey;

    @Override
    public BufferedReader getReader(String filename) {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 amazonS3 = new AmazonS3Client(awsCredentials);
        S3Object s3Object = amazonS3.getObject(bucketName, filename);
        InputStream input = s3Object.getObjectContent();
        return new BufferedReader(new InputStreamReader(input));
    }
}
