package IntegrationTests.HelpUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-25-07
 * The DBOperations class represents main DB operation.
 */

public class DBOperations {

    private static Statement statement = setupDBConnection();
    private static Connection connection;
    private static final String JDBCPropertyFile = "config.properties";

    /**
     * Open a connection to 'Customer' DB, create a connection object.
     *
     * @return the statement object.
     */

    public static Statement setupDBConnection() {
        Properties prop = new Properties();
        try {
           // InputStream input = getResourceAsStream(JDBCPropertyFile);
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

    /**
     * The isExist() method is intended to verify whether a record exist or not.
     *
     * @param 'idEntities' a variable of type String.
     * @return a Boolean data type
     */

    public static boolean isExist(String idEntities, String idFields) {
        Boolean isRecord = false;
        try {
            statement.executeQuery("SELECT * FROM entities WHERE id = '" + idEntities + "'");
            statement.executeQuery("SELECT * FROM fields WHERE id = '" + idFields + "'");
            ResultSet rs = statement.getResultSet();
            if (!rs.next()) {
                System.out.println("Record with" + idEntities + " and " +  idFields + " IDs is not exist.");
                isRecord = true;
            } else {
                System.out.println("Record with that ID is exist FFF.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRecord;
    }

    /**
     * Creates record in DB
     */

    public static void createRecord() {
        if (isExist("ENTITIESID", "FIELDSID")) {
            try {
                statement.execute(" INSERT INTO entities (id, name, schema_name, table_name) VALUES ('ENTITIESID', 'NameEntities','SchemaNameEntities' , 'TableNameEntities');");
                statement.execute(" INSERT INTO fields (id, name, column_name, type, length, entity_id) VALUES ('FIELDSID', 'NameFields', 'Column_NameFields', 'INT', 100, 'ENTITIESID')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record was created");
        } else {
            System.out.println("Record was not created");
        }
    }


    /**
     * The deleteRecord()  method is intended for deleting record from DB.
     */

    public static void deleteRecord(String fieldsId, String entitiesId) {
        if (isExist(fieldsId, entitiesId)) {
            try {
                statement.execute("DELETE FROM fields WHERE id = '" + fieldsId + "'");
                statement.execute("DELETE FROM entities WHERE id = '" + entitiesId + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record was deleted");
        } else {
            System.out.println("Record is not exist");
        }
    }
}
