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

        FileOutputStream out = null;
        Properties fromGitProperties = new Properties();

        try {
            out = new FileOutputStream("C:\\Program Files (x86)\\Jenkins\\workspace\\jobExporter\\service\\src\\main\\database.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fromGitProperties.setProperty("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        fromGitProperties.setProperty("javax.persistence.jdbc.url", "jdbc:sqlserver://localhost:1433;databaseName=EntityMetadata");
        fromGitProperties.setProperty("javax.persistence.jdbc.user", "sa");
        fromGitProperties.setProperty("javax.persistence.jdbc.password", "zippyZippy_1");


        fromGitProperties.setProperty("administration.javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        fromGitProperties.setProperty("administration.javax.persistence.jdbc.url", "jdbc:sql:server://localhost:1433;databaseName=Administration");
        fromGitProperties.setProperty("administration.javax.persistence.jdbc.user", "sa");
        fromGitProperties.setProperty("administration.javax.persistence.jdbc.password","zippyZippy_1");

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
