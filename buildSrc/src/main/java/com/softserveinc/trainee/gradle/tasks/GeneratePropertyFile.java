package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.*;

/**
 * Created by sriznych on 15.11.2016.
 */

public class GeneratePropertyFile extends DefaultTask {

    @TaskAction
    public void setupPropertyFile() {
        FileInputStream local = null;
        try {
            local = new FileInputStream("C:\\localCredentialFile.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties localProperties = new Properties();
        try {
            localProperties.load(local);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            local.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\Users\Administrator\.jenkins\workspace\tryTry\service\src\main\resources" +
                    "database.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties fromGitProperties = new Properties();
        try {
            fromGitProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("C:\Users\Administrator\.jenkins\workspace\tryTry\service\src\main\resources" +
                    "database.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fromGitProperties.setProperty("password", localProperties.getProperty("password"));
        fromGitProperties.setProperty("login", localProperties.getProperty("login"));
        try {
            fromGitProperties.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
