package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.util.Properties;

/**
 * Created by vberv on 7/28/2016.
 */
public class StartTomcat extends DefaultTask {

    @TaskAction
    public void startTomcat(){

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "catalina.bat", "start");

        /*Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("tomcat.properties");
        } catch (FileNotFoundException e) {
            throw new GradleException("Coundn't read file");
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coundn't find path to Tomcat");
        }
        String pathToTomcat = properties.getProperty("pathToTomcat");

        pb.directory(new File(pathToTomcat));*/
        Process proc = null;
        try {
            proc = pb.start();
        } catch (IOException e) {
            throw new GradleException("Coundn't start proces");
        }
        try {
            proc.waitFor();
        } catch (InterruptedException e) {
            throw new GradleException("Coundn't start proces caused by 'InterruptedException'");
        }

    }
}
