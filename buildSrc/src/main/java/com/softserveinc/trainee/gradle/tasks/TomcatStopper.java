package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TomcatStopper extends DefaultTask {

    @TaskAction
    public void stop(){
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream("tomcat.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coudn't get path to tomcat");
        }
        String path = properties.getProperty("pathToTomcat");
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("cmd /c " + path + "/bin/shutdown.bat");
        } catch (IOException e) {
            throw new GradleException("Coudn't stop tomcat");
        }
    }
}
