package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.EntityUtil.EntityUtil;
import com.softserveinc.trainee.dao.RequestTaskDao;
import com.softserveinc.trainee.entity.Entity;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Repository("requestTaskDao")
public class RequestTaskDaoImpl implements RequestTaskDao{

    private static final String PATH_TO_DATABASE_PROPERTIES = "service/src/main/resources/database.properties";
    private static final String MS_SQL_SERVER_ADDRES = "jdbc:sqlserver://localhost;";
    private static final String USERNAME_KEY_PROPERTIES = "javax.persistence.jdbc.user";
    private static final String PASSWORD_KEY_PROPERTIES = "javax.persistence.jdbc.password";

    @Override
    public void createRequestTask(String id, String description, String owner) {

        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_DATABASE_PROPERTIES)){
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot load properties file");
        }

        String username = properties.getProperty(USERNAME_KEY_PROPERTIES);
        String password = properties.getProperty(PASSWORD_KEY_PROPERTIES);
        Date currentdate = new Date();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = dt1.format(currentdate);
        String sql = "INSERT INTO request_tasks (owner, date, aim, description) VALUES ('" + owner + "', '" + date + "', '" + id + "', '" + description + "')";
        try(Connection connection = DriverManager.getConnection(MS_SQL_SERVER_ADDRES + "databaseName=CustomerRequest" + ";user=" + username + ";password=" + password)){
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch (SQLException e) {
            System.out.println("Cannot execute sql query");
        }
    }

    @Override
    public void createEntityTable(Entity entity) {
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_DATABASE_PROPERTIES)){
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot load properties file");
        }
        String username = properties.getProperty(USERNAME_KEY_PROPERTIES);
        String password = properties.getProperty(PASSWORD_KEY_PROPERTIES);
        String queryDatabase = EntityUtil.createDb(entity);
        String queryTable = EntityUtil.createTable(entity);
        try(Connection connection = DriverManager.getConnection(MS_SQL_SERVER_ADDRES + "user=" + username + ";password=" + password)){
            PreparedStatement ps = connection.prepareStatement(queryDatabase);
            ps.executeUpdate();
            ps = connection.prepareStatement(queryTable);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Cannot execute sql query");
        }
    }
}