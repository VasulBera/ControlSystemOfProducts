package UISuiteTest;

import IntegrationTests.HelpUtils.DBOperations;
import UISuiteTest.LogInData.CreateEntityData;
import UISuiteTest.LogInData.DataForDB;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by sriznych on 05.09.2016.
 */

public class SelectDB {

    private static Statement statement = setupDBConnection();
    private static Connection connection;
    private static final String JDBCPropertyFile = "configAmazon.properties";
    private static ResultSet resultSet;

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    public static Statement setupDBConnection() {
        Properties prop = new Properties();
        try {
            InputStream input = DBOperations.class.getClassLoader().getResourceAsStream(JDBCPropertyFile);
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
            statement = connection.createStatement();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static CreateEntityData selectEntityTableFromAmazonDB(String idValue) {
        CreateEntityData createEntityData = new CreateEntityData();
        try {
            String sql = "SELECT name, table_name, schema_name FROM [EntityMetadata].[dbo].[entities] where id = '" + idValue + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                createEntityData.setEntityName(resultSet.getString("name"));
                createEntityData.setEntityTableName(resultSet.getString("table_name"));
                createEntityData.setEntitySchemaName(resultSet.getString("schema_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return createEntityData;
    }

    public static DataForDB selectFullRecordFromAmazonDB(String idValue) {
        DataForDB dataForDB = new DataForDB();
        try {
            String sql = "SELECT entities.name as nameEntity, entities.table_name, entities.schema_name, fields.name as nameField, fields.column_name as columnField, fields.type as typeField, fields.length as lengthField FROM [EntityMetadata].[dbo].[entities] inner join [EntityMetadata].[dbo].[fields]  on  entities.id = fields.entity_id where entities.id = '" + idValue + "'";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                dataForDB.setEntityNameDB(resultSet.getString("nameEntity"));
                dataForDB.setEntitySchemaNameDB(resultSet.getString("schema_name"));
                dataForDB.setEntityTableNameDB(resultSet.getString("table_name"));
                dataForDB.setFieldNameDB(resultSet.getString("nameField"));
                dataForDB.setFieldColumnNameDB(resultSet.getString("columnField"));
                dataForDB.setDataTypeDB(resultSet.getString("typeField"));
                dataForDB.setLengthValueDB(resultSet.getString("lengthField"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataForDB;
    }

    public static boolean isExistEntity(String idEntities) {
        Boolean isRecord = true;
        try {
            statement.executeQuery("SELECT * FROM entities WHERE id = '" + idEntities + "'");
            ResultSet rs = statement.getResultSet();
            if (!rs.next()) {
                System.out.println("Record with" + idEntities + " and IDs is not exist.");
                isRecord = false;
            } else {
                System.out.println("Record with that ID is exist FFF.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRecord;
    }

    public static boolean isExistFullRecord(String idEntities, String idFields) {
        Boolean isRecord = false;
        try {
            statement.executeQuery("SELECT * FROM entities WHERE id = '" + idEntities + "'");
            statement.executeQuery("SELECT * FROM fields WHERE id = '" + idFields + "'");
            ResultSet rs = statement.getResultSet();
            if (!rs.next()) {
                System.out.println("Record with" + idEntities + " and " + idFields + " IDs is not exist.");
                isRecord = true;
            } else {
                System.out.println("Record with that ID is exist FFF.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRecord;
    }

    @Test
    public void ff(){

    }
}
