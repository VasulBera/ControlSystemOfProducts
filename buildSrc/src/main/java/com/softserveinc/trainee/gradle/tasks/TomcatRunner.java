package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class TomcatRunner extends DefaultTask {

    @TaskAction
    public void run(){
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream("tomcat.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coudn't get path to tomcat");
        }
        String path = properties.getProperty("pathToTomcat");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println(path + "\\bin\\startup.bat");
            Process p1 = runtime.exec("cmd /c " + path + "/bin/startup.bat");
        } catch (IOException e) {
           throw new GradleException("Coudn't start tomcat");
        }
    }
}
