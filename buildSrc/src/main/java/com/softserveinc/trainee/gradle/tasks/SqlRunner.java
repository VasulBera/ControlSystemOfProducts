
package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlRunner extends DefaultTask{

    private static final String PATH_TO_STORED_PROCEDURE = "service/src/main/resources/StoredProcedure";
    private static final String PATH_TO_DATABASE_PROPERTIES = "service/src/main/resources/database.properties";
    private static final String MS_SQL_SERVER_ADDRES = "jdbc:sqlserver://localhost;";
    private static final String USERNAME_KEY_PROPERTIES = "javax.persistence.jdbc.user";
    private static final String PASSWORD_KEY_PROPERTIES = "javax.persistence.jdbc.password";


    private static String readFile(File pathFile){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }catch (IOException e) {
            throw new GradleException("Coundn't read sql file");
        }
        return stringBuilder.toString();
    }

    private static String getUsername(){
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_DATABASE_PROPERTIES)){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coundn't get username from database.properties");
        }
        return properties.getProperty(USERNAME_KEY_PROPERTIES);
    }

    private static String getPassword(){
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_DATABASE_PROPERTIES)){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new GradleException("Coundn't get password from database.properties");
        }
        return properties.getProperty(PASSWORD_KEY_PROPERTIES);
    }

    @TaskAction
    public void runSqlFiels(){
        String username = getUsername();
        System.out.println("user name is = " + username + " ...");
        String password = getPassword();
        try(Connection connection = DriverManager.getConnection(MS_SQL_SERVER_ADDRES + "user=" + username + ";password=" + password)){
            System.out.println("created connection to DB ...");
            Statement st = connection.createStatement();
            File mainDirectory = new File(PATH_TO_STORED_PROCEDURE);
            File[] directories = mainDirectory.listFiles();
            for(File directory: directories){
                if(directory.isDirectory()){
                    for(File file: directory.listFiles()){
                        System.out.println("running file " + file.getName());
                        st.execute(readFile(file));
                    }
                }else {
                    System.out.println("running file " + directory.getName());
                    st.execute(readFile(directory));
                }
            }
        } catch (SQLException e) {
            throw new GradleException("Coundn't execute sql file");
        }
    }

}
