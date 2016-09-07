package com.softserveinc.trainee.dao.Impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.softserveinc.trainee.dao.CsvFileDao;
import com.softserveinc.trainee.entity.metadata.Entity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.Iterator;
import java.util.Properties;

@Repository("csvFileDao")
public class CsvFileDaoImpl implements CsvFileDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String PATH_TO_S3 = "service\\src\\main\\resources\\s3.properties";
    private static final String BUCKET_NAME = "bucketName";
    private static final String S3_KEY_ID = "AWSAccessKeyId";
    private static final String S3_SECRET_KEY = "AWSSecretKey";
    private static final String FILE_EXTENSION = ".csv";
    private static final Integer RECORD_AMOUNT = 1_000;
    private static final Integer RECORD_ORIGIN = 1;
    private static Properties properties = new Properties();

    public void uploadData(Entity entity){
        Iterable<CSVRecord> records = getAllRecords(entity);
        int recordIndex = RECORD_ORIGIN;
        StringBuilder stringBuilder = new StringBuilder();
        String recordInsertPrefix = "";
        for(CSVRecord record : records) {
            Iterator<String> it = record.iterator();
            String prefix = "";
            stringBuilder.append(recordInsertPrefix);
            stringBuilder.append("(");
            for(int i = 0; i < record.size(); i++) {
                stringBuilder.append(prefix);
                stringBuilder.append("'" + it.next() + "'");
                prefix = ",";
            }
            stringBuilder.append(")\n");
            recordInsertPrefix = ",";
            if(recordIndex == RECORD_AMOUNT) {
                jdbcTemplate.execute("INSERT INTO client." + entity.getTableName() + " VALUES\n" + stringBuilder);
                stringBuilder = new StringBuilder();
                recordIndex = RECORD_ORIGIN;
                recordInsertPrefix = "";
            }
            recordIndex++;

        }
        if(!stringBuilder.toString().isEmpty()) {
            jdbcTemplate.execute("INSERT INTO client." + entity.getTableName() + " VALUES\n" + stringBuilder);
        }
    }

    private static void initializeProperties(){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PATH_TO_S3);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("CsvFileDaoImpl.uploadData(String name): Cannot find properties file with amazon s3 configs");
        } catch (IOException e) {
            System.out.println("CsvFileDaoImpl.uploadData(String name): Cannot load properties file");
        }
    }

    private static Iterable<CSVRecord> getAllRecords(Entity entity){
        initializeProperties();
        String bucketName = properties.getProperty(BUCKET_NAME);
        String id = properties.getProperty(S3_KEY_ID);
        String key = properties.getProperty(S3_SECRET_KEY);
        AWSCredentials awsCredentials = new BasicAWSCredentials(id, key);
        AmazonS3 amazonS3 = new AmazonS3Client(awsCredentials);
        String filename = entity.getTableName() + FILE_EXTENSION;
        S3Object s3Object = amazonS3.getObject(bucketName, filename);
        InputStream input = s3Object.getObjectContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Iterable<CSVRecord> records = null;
        try {
            records = CSVFormat.EXCEL.parse(reader);
        } catch (IOException e) {
            System.out.println("CsvFileDaoImpl.getRecords(Reader reader): cannot parse scv file");
        }
        return records;
    }
}
