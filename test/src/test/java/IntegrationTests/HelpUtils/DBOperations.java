package IntegrationTests.HelpUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-25-07
 * The DBOperations class represents main DB operation.
 */

public class DBOperations {

    private static Connection connection;
    private static final String metaDataPropertyFile = "EntityMetadataDB.properties";
    private static final String customTablesDBPropertyFile = "CustomTablesDB.properties";
    private static Statement statement;
    private static ResultSet resultSet;
    private static  String uploadRequestJobResult;

    /**
     * Open a connection to 'Customer' DB, create a connection object.
     *
     * @return the statement object.
     */

    public static Connection setupDBConnection(String customPropertyFile) {
        Properties prop = new Properties();
        try {
            InputStream input = DBOperations.class.getClassLoader().getResourceAsStream(customPropertyFile);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
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
            statement = setupDBConnection(metaDataPropertyFile).createStatement();
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

    /**
     * The createFullRecord() method creates record in EntityMetadata DB using dbo.entities and dbo.field tables
     */

    public static void createFullRecord() {

        SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if (isExist("SCHEMATABLE", "SCHEMATABLECOLUMN_NAMEFIELDS")) {
            try {
                statement = setupDBConnection(metaDataPropertyFile).createStatement();
                statement.execute("INSERT INTO entities (id, name, schema_name, table_name, full_upload_data, created_date)  VALUES ('SCHEMATABLE', 'Name','Schema', 'Table', '0', '" + simpleDateFormater.format(Calendar.getInstance().getTime()) + "');");
                statement.execute("INSERT INTO fields (id, name, column_name, type, length, is_unique, entity_id, created_date) VALUES ('SCHEMATABLECOLUMN_NAMEFIELDS', 'NameFields'," +
                        " 'Column_NameFields', 'NVARCHAR', 100, '0', 'SCHEMATABLE', '" + simpleDateFormater.format(Calendar.getInstance().getTime()) + "')");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record was created");
        } else {
            System.out.println("Record was not created");
        }
    }

    /**
     * The createRecordIntoEntitiesTable() method creates record in EntityMetadata DB using dbo.entities table
     */

    public static void createRecordIntoEntitiesTable() {
        SimpleDateFormat simpleDateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        if (isExist("ENTITYSCHEMAENTITYTABLE", " ")) {
            try {
                statement = setupDBConnection(metaDataPropertyFile).createStatement();
                statement.execute("INSERT INTO entities (id, name, schema_name, table_name, full_upload_data, created_date)  VALUES ('ENTITYSCHEMAENTITYTABLE', 'EntityName','EntitySchema', 'EntityTable', '0', '" + simpleDateFormater.format(Calendar.getInstance().getTime()) + "');");
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
                statement = setupDBConnection(metaDataPropertyFile).createStatement();
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

    public static boolean isTableExist(String tableName) {
        Boolean isTable = false;
        try {
            DatabaseMetaData metaData = setupDBConnection(customTablesDBPropertyFile).getMetaData();
            ResultSet resultSet;
            resultSet = metaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                System.out.println("Table exist");
                isTable = true;
            } else {
                System.out.println("Table NOT  exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTable;
    }

    public static boolean isColumnExist(String tableName, String columnName) {
        Boolean isTable = false;
        try {
            DatabaseMetaData metaData = setupDBConnection(customTablesDBPropertyFile).getMetaData();
            ResultSet resultSet;
            resultSet = metaData.getColumns(null, null, tableName, columnName);
            if (resultSet.next()) {
                System.out.println("Column exist");
                isTable = true;
            } else {
                System.out.println("Column NOT  exist");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTable;
    }

    public static String getRecordFromCustomTablesDB() {

        try {
            statement = setupDBConnection(metaDataPropertyFile).createStatement();
            String sql = "SELECT * FROM [CustomTables].[client].[CARSS]";
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                uploadRequestJobResult = resultSet.getString("JJJoo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uploadRequestJobResult;
    }
}




