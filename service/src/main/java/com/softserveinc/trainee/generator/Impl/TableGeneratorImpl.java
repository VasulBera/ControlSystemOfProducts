package com.softserveinc.trainee.generator.Impl;

import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.generator.TableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableGeneratorImpl implements TableGenerator {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String DB_NAME = "CustomTables";

    @Override
    public void createTable(Entity entity) {
        jdbcTemplate.execute(getCreateSqlQuery(entity));
    }

    @Override
    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity) {
        jdbcTemplate.execute(getUpdateSqlQuery(previousStateEntity, entity));
        if(entity.changeUniqueField(previousStateEntity)){
            jdbcTemplate.execute(changeUniqueFieldSqlQuery(entity));
        }
    }

    @Override
    public void deleteTable(String tableName) {
        jdbcTemplate.execute("DROP TABLE " + tableName);
    }

    private String getCreateSqlQuery(Entity entity){
        StringBuilder query = new StringBuilder()
                .append("IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = '" + entity.getSchemaName() + "' )\n")
                .append("BEGIN\nUSE CustomTables;\n")
                .append("EXEC sp_executesql N'CREATE SCHEMA " + entity.getSchemaName() + ";';\n")
                .append("END\n")
                .append("IF OBJECT_ID (N'CustomTables." + entity.genereateShemaWithTable() + "', N'U') IS NULL\n")
                .append("CREATE TABLE " + entity.createFullTableName() +" (\nid INT IDENTITY(1,1) PRIMARY KEY, \n");
        String prefix="";
        StringBuilder alterTableQuery = new StringBuilder();
        if(entity.getFieldList() != null) {
            for (Field field : entity.getFieldList()) {
                if(field.isUnique()){
                    alterTableQuery.append("\nALTER TABLE " + entity.createFullTableName() + "\nADD CONSTRAINT " + entity.getConstraintName() + " UNIQUE (" + field.getColumnName() + ")");
                }
                query.append(prefix);
                query.append(field.generateColumnSql() + " NOT NULL");
                prefix = ",\n";
            }
        }
        query.append("\n)\n");
        query.append(alterTableQuery);
        return query.toString();
    }

    private String getUpdateSqlQuery(PreviousStateEntity previousStateEntity, Entity entity){
        StringBuilder query = new StringBuilder()
                .append("USE CustomTables;\n")
                .append("IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = '" + entity.getSchemaName() + "' )\n")
                .append("BEGIN\n")
                .append("EXEC sp_executesql N'CREATE SCHEMA " + entity.getSchemaName() + ";';\n")
                .append("END\n")
                .append("ALTER SCHEMA " + entity.getSchemaName() + " TRANSFER " + previousStateEntity.getSchemaName() + "." + previousStateEntity.getTableName() + "\n")
                .append("EXEC sp_rename '" + entity.getSchemaName() + "." + previousStateEntity.getTableName() + "', '" + entity.getTableName() + "';\n");

        for(PreviousStateField previusStateField: previousStateEntity.getFieldList()){
            for(Field field: entity.getFieldList()){
                if(field.getId().equals(previusStateField.getId()) && !field.getColumnName().equals(previusStateField.getColumnName())){
                    query.append("EXEC sp_rename '" + entity.genereateShemaWithTable() + "." + previusStateField.getColumnName() + "', '" + field.getColumnName() + "', 'COLUMN';\n");
                }
            }
        }
        boolean isExsists = false;
        for(Field previousField: previousStateEntity.getFields()){
            for(Field field: entity.getFieldList()){
                if(previousField.getId().equals(field.getId())){
                    isExsists = true;
                }
            }
            if(!isExsists){
                if(previousField.isUnique()){
                    query.append("DECLARE @DELETE_SQL VARCHAR(4000);\n")
                            .append("SET @DELETE_SQL = 'ALTER TABLE " + entity.createFullTableName() + " DROP CONSTRAINT |ConstraintName|'\n")
                            .append("SET @DELETE_SQL = REPLACE(@DELETE_SQL, '|ConstraintName|', (SELECT k.name FROM sys.tables t INNER JOIN sys.key_constraints k ON t.object_id = k.parent_object_id WHERE t.name = '" + entity.getTableName() +"' AND k.type = 'UQ')\n")
                            .append(")\n")
                            .append("EXEC (@DELETE_SQL)\n");
                }
                query.append("ALTER TABLE " + entity.genereateShemaWithTable() + " DROP COLUMN " + previousField.getColumnName() + ";\n");
            }
            isExsists = false;
        }
        for(Field field: entity.getFieldList()){
            for(Field previousField: previousStateEntity.getFields()){
                if(field.getId().equals(previousField.getId())){
                    isExsists = true;
                }
            }
            if(!isExsists){
                query.append("ALTER TABLE " + entity.genereateShemaWithTable() + " ADD " + field.generateColumnSql() + " NOT NULL;\n");
            }
            isExsists = false;
        }
        return query.toString();
    }

    private String changeUniqueFieldSqlQuery( Entity entity){
        StringBuilder query = new StringBuilder();
        query.append("SET XACT_ABORT ON\n")
                .append("BEGIN TRANSACTION changeUniqueField\n")
                .append("SELECT * INTO [" + DB_NAME + "].[" + entity.getSchemaName() + "].[" + entity.getTableName() + "_tmp] FROM " + entity.createFullTableName() + " WHERE 1 = 2\n")
                .append("INSERT INTO [" + DB_NAME + "].[" + entity.getSchemaName() + "].[" + entity.getTableName() + "_tmp] ( " + entity.joinColumnNames() + " ) SELECT " + entity.joinColumnNames() + " FROM " + entity.createFullTableName() +"\n")
                .append("DELETE FROM " + entity.createFullTableName() + "\n")
                .append("DECLARE @SQL VARCHAR(4000);\n")
                .append("SET @SQL = 'ALTER TABLE " + entity.createFullTableName() + " DROP CONSTRAINT |ConstraintName|'\n")
                .append("SET @SQL = REPLACE(@SQL, '|ConstraintName|', (SELECT k.name FROM sys.tables t INNER JOIN sys.key_constraints k ON t.object_id = k.parent_object_id WHERE t.name = '" + entity.getTableName() +"' AND k.type = 'UQ')\n")
                .append(")\n")
                .append("EXEC (@SQL)\n");
        for(Field field: entity.getFieldList()){
            if(field.isUnique()){
                query.append("ALTER TABLE " + entity.createFullTableName() + "\nADD CONSTRAINT " + entity.getTableName().toLowerCase() + "_" + field.getColumnName() + "_Primary_key UNIQUE (" + field.getColumnName() + ")\n");
            }
        }
        query.append("INSERT INTO " + entity.createFullTableName() + " ( " + entity.joinColumnNames() + " ) " + "SELECT " + entity.joinColumnNames() + " FROM [" + DB_NAME + "].[" + entity.getSchemaName() + "].[" + entity.getTableName() + "_tmp]\n")
                .append("DROP TABLE [" + DB_NAME + "].[" + entity.getSchemaName() + "].[" + entity.getTableName() + "_tmp]\n")
                .append("COMMIT TRANSACTION changeUniqueField");
        return query.toString();
    }
}
