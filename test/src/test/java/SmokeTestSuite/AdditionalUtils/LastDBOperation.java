package SmokeTestSuite.AdditionalUtils;

import IntegrationTests.HelpUtils.DBOperations;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Salome on 14.12.2016.
 */

public class LastDBOperation {

    private static Statement statement;
    private static Connection connection;
    private static final String customTablesDB = "CustomTablesDB.properties";
    private static ResultSet resultSet;

    private String idValue;
    private String jjjooValue;
    private String totalString;
    private String forCSVFile;

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    public static Connection setupDBConnection() {
        Properties prop = new Properties();
        try {
            InputStream input = DBOperations.class.getClassLoader().getResourceAsStream(customTablesDB);
            prop.load(input);
            StringBuilder str = new StringBuilder();
            str.append(prop.getProperty("dburl"));
            str.append(prop.getProperty("host"));
            str.append(";databaseName=");
            str.append(prop.getProperty("databaseName"));
            str.append("user=");
            str.append(prop.getProperty("user"));
            str.append(";password=");
            str.append(prop.getProperty("password"));
            connection = DriverManager.getConnection(String.valueOf(str));
           // statement = connection.createStatement();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Test
    public void selectCustomTablesDB() {

        try {
            statement = setupDBConnection().createStatement();
                String sql = "ALTER DATABASE CustomTables SET SINGLE_USER WITH ROLLBACK IMMEDIATE;";
                statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
}