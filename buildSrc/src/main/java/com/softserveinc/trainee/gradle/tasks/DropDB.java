package com.softserveinc.trainee.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.softserveinc.trainee.gradle.tasks.SqlRunner.getPassword;
import static com.softserveinc.trainee.gradle.tasks.SqlRunner.getUsername;
import static com.softserveinc.trainee.gradle.tasks.SqlRunner.readFile;

/**
 * Created by Salome on 20.12.2016.
 */
public class DropDB  extends DefaultTask {

   /* static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static final String DB_URL = "jdbc:sqlserver://52.34.34.95:1433;";
    static final String USER = "sa";
    static final String PASS = "zippyZippy_1";*/

    private static final String PATH_TO_STORED_PROCEDURE = "service/src/main/resources/DropDB";
    private static final String PATH_TO_DATABASE_PROPERTIES = "service/src/main/resources/database.properties";
    private static final String MS_SQL_SERVER_ADDRES = "jdbc:sqlserver://52.34.34.95;";
    private static final String USERNAME_KEY_PROPERTIES = "javax.persistence.jdbc.user";
    private static final String PASSWORD_KEY_PROPERTIES = "javax.persistence.jdbc.password";

    @TaskAction
    public void dropDataBases(){
      /*  Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "DROP DATABASE EntityMetadata";
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }*/
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
