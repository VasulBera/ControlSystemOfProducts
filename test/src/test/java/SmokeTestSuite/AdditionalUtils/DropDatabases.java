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

public class DropDatabases {

    private static Statement statement;
    private static Connection connection;
    private static final String customTablesDB = "CustomTablesDB.properties";
    private static final String entityMetadataDB = "EntityMetadataDB.properties";
    private static ResultSet resultSet;

    private String idValue;
    private String jjjooValue;
    private String totalString;
    private String forCSVFile;

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    public static Connection setupDBConnection(String propertyFile) {
        Properties prop = new Properties();
        try {
            InputStream input = DBOperations.class.getClassLoader().getResourceAsStream(propertyFile);
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
    public void dropCustomTablesDB() {

        try {
            statement = setupDBConnection(customTablesDB).createStatement();
                String sql =
                        "DROP DATABASE CUSTOMTABLES";
                statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void dropEntityMetadataDB() {
        try {
            statement = setupDBConnection(entityMetadataDB).createStatement();
            String sql =

                        " DROP DATABASE EntityMetadata";
         /* String sql = "DROP DATABASE EntityMetadata";*/
            statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}