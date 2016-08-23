package com.softserveinc.trainee.EntityUtil;

import com.softserveinc.trainee.entity.metadata.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class EntityUtil {

    private static final String PATH_TO_DATABASE_PROPERTIES = "service\\src\\main\\resources\\database.properties";
    private static final String MS_SQL_SERVER_ADDRES = "jdbc:sqlserver://localhost;databaseName=CustomTables;";
    private static final String USERNAME_KEY_PROPERTIES = "javax.persistence.jdbc.user";
    private static final String PASSWORD_KEY_PROPERTIES = "javax.persistence.jdbc.password";

    public static String createTable(Entity entity){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USE CustomTables;  \n" +
                "IF NOT EXISTS (SELECT schema_name \n" +
                "    FROM information_schema.schemata \n" +
                "    WHERE schema_name = '" + entity.getSchemaName() + "' )\n" +
                "BEGIN\n" +
                "    EXEC sp_executesql N'CREATE SCHEMA " + entity.getSchemaName() + ";';\n" +
                "END ");
        stringBuilder.append("\n");
        stringBuilder.append("USE CustomTables;  \n" +
                "IF OBJECT_ID (N'" + entity.getSchemaName() + "." + entity.getTableName() + "', N'U') IS NULL  \n" +
                "CREATE TABLE " + entity.getSchemaName() + "." + entity.getTableName() + " (\n");
        String prefix="";
        for(Field f: entity.getFieldList()){
            stringBuilder.append(prefix);
            if(f.getType() == FieldType.NVARCHAR) {
                stringBuilder.append(f.getColumnName() + " " + f.getType() + "(" + f.getLength() + ")");
            }else{
                stringBuilder.append(f.getColumnName() + " " + f.getType());
            }
            prefix = ",\n";
        }
        stringBuilder.append("\n)");
        return stringBuilder.toString();
    }

    public static void generateTable(Entity entity) {
        String sql = createTable(entity);
        runSql(sql);
    }

    public static void updateTable(PreviousStateEntity previousStateEntity, Entity entity){
        String sql = updateTableSql(previousStateEntity, entity);
        runSql(sql);

    }

    public static String updateTableSql(PreviousStateEntity previousStateEntity, Entity entity){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USE CustomTables;  \n");
        stringBuilder.append("IF NOT EXISTS (SELECT schema_name \n" +
                "    FROM information_schema.schemata \n" +
                "    WHERE schema_name = '" + entity.getSchemaName() + "' )\n");
        stringBuilder.append("BEGIN\n");
        stringBuilder.append("    EXEC sp_executesql N'CREATE SCHEMA " + entity.getSchemaName() + ";';\n");
        stringBuilder.append("END  \n");
        stringBuilder.append("    ALTER SCHEMA " + entity.getSchemaName() + " TRANSFER " + previousStateEntity.getSchemaName() + "." + previousStateEntity.getTableName() + " \n");
        stringBuilder.append("    EXEC sp_rename '" + entity.getSchemaName() + "." + previousStateEntity.getTableName() + "', '" + entity.getTableName() + "';\n");
        main: for(PreviousStateField f: previousStateEntity.getFieldList()){
            for(Field field: entity.getFieldList()){
                if(f.getId().equals(field.getId())){
                    if(!f.getColumnName().equals(field.getColumnName())){
                        stringBuilder.append("EXEC sp_rename '" + entity.getSchemaName() + "." + entity.getTableName() + "." + f.getColumnName() + "', '" + field.getColumnName() + "' , 'COLUMN'\n");
                    }
                    continue main;
                }
            }
            stringBuilder.append("ALTER TABLE " + entity.getSchemaName() + "." + entity.getTableName() + " DROP COLUMN " + f.getColumnName() +" ;  \n");
        }
        main: for(Field field: entity.getFieldList()){
            for(PreviousStateField f: previousStateEntity.getFieldList()){
                if(field.getId().equals(f.getId())){
                    continue main;
                }
            }
            if(field.getType() != FieldType.NVARCHAR) {
                stringBuilder.append("ALTER TABLE " + entity.getSchemaName() + "." + entity.getTableName() + " ADD " + field.getColumnName() + " " + field.getType() + " NULL;");
            }else{
                stringBuilder.append("ALTER TABLE " + entity.getSchemaName() + "." + entity.getTableName() + " ADD " + field.getColumnName() + " " + field.getType() + "(" + field.getLength() + ")" + " NULL;");
            }
        }
        return stringBuilder.toString();
    }
    private static void runSql(String sql){
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream(PATH_TO_DATABASE_PROPERTIES)){
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot load properties file");
        }
        String username = properties.getProperty(USERNAME_KEY_PROPERTIES);
        String password = properties.getProperty(PASSWORD_KEY_PROPERTIES);
        try(Connection connection = DriverManager.getConnection(MS_SQL_SERVER_ADDRES + "user=" + username + ";password=" + password)){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Cannot execute sql query");
        }
    }
}
