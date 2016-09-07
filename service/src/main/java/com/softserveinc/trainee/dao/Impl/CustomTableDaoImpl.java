package com.softserveinc.trainee.dao.Impl;

import com.softserveinc.trainee.dao.CustomTableDao;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("customTableDao")
public class CustomTableDaoImpl implements CustomTableDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void generateTable(Entity entity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USE CustomTables;  \n");
        stringBuilder.append("IF NOT EXISTS (SELECT schema_name \n");
        stringBuilder.append("FROM information_schema.schemata \n");
        stringBuilder.append("WHERE schema_name = '");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append("' )\n");
        stringBuilder.append("BEGIN\n");
        stringBuilder.append("EXEC sp_executesql N'CREATE SCHEMA ");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append(";';\n");
        stringBuilder.append("END ");
        stringBuilder.append("\n");
        stringBuilder.append("USE CustomTables;  \n");
        stringBuilder.append("IF OBJECT_ID (N'");
        stringBuilder.append(entity.genereateShemaWithTable());
        stringBuilder.append("', N'U') IS NULL  \n");
        stringBuilder.append("CREATE TABLE ");
        stringBuilder.append(entity.genereateShemaWithTable());
        stringBuilder.append(" (\n");
        String prefix="";
        if(entity.getFieldList() != null) {
            for (Field field : entity.getFieldList()) {
                stringBuilder.append(prefix);
                stringBuilder.append(field.generateColumnSql());
                prefix = ",\n";
            }
        }
        stringBuilder.append("\n)");
        jdbcTemplate.execute(stringBuilder.toString());
    }

    public void updateTable(PreviousStateEntity previousStateEntity, Entity entity){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("USE CustomTables;  \n");
        stringBuilder.append("IF NOT EXISTS (SELECT schema_name \n");
        stringBuilder.append("    FROM information_schema.schemata \n");
        stringBuilder.append("    WHERE schema_name = '");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append("' )\n");
        stringBuilder.append("BEGIN\n");
        stringBuilder.append("    EXEC sp_executesql N'CREATE SCHEMA ");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append(";';\n");
        stringBuilder.append("END  \n");
        stringBuilder.append("    ALTER SCHEMA ");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append(" TRANSFER ");
        stringBuilder.append(previousStateEntity.getSchemaName());
        stringBuilder.append(".");
        stringBuilder.append(previousStateEntity.getTableName());
        stringBuilder.append(" \n");
        stringBuilder.append("    EXEC sp_rename '");
        stringBuilder.append(entity.getSchemaName());
        stringBuilder.append(".");
        stringBuilder.append(previousStateEntity.getTableName());
        stringBuilder.append("', '");
        stringBuilder.append(entity.getTableName());
        stringBuilder.append("';\n");
        main: for(PreviousStateField previousStateField: previousStateEntity.getFieldList()){
            for(Field field: entity.getFieldList()){
                if(previousStateField.getId().equals(field.getId())){
                    if(!previousStateField.getColumnName().equals(field.getColumnName())){
                        stringBuilder.append("EXEC sp_rename '");
                        stringBuilder.append(entity.getSchemaName());
                        stringBuilder.append(".");
                        stringBuilder.append(entity.getTableName());
                        stringBuilder.append(".");
                        stringBuilder.append(previousStateField.getColumnName());
                        stringBuilder.append("', '");
                        stringBuilder.append(field.getColumnName());
                        stringBuilder.append("' , 'COLUMN'\n");
                    }
                    continue main;
                }
            }
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(entity.genereateShemaWithTable());
            stringBuilder.append(" DROP COLUMN ");
            stringBuilder.append(previousStateField.getColumnName());
            stringBuilder.append(" ;  \n");
        }
        mainField: for(Field field: entity.getFieldList()){
            for(PreviousStateField f: previousStateEntity.getFieldList()){
                if(field.getId().equals(f.getId())){
                    continue mainField;
                }
            }
            stringBuilder.append("ALTER TABLE ");
            stringBuilder.append(entity.genereateShemaWithTable());
            stringBuilder.append(" ADD ");
            stringBuilder.append(field.generateColumnSql());
        }
        jdbcTemplate.execute(stringBuilder.toString());
    }

    public void deleteAllRecord(Entity entity){
        String sql = "DELETE FROM " + entity.getSchemaName() + "." + entity.getTableName();
        jdbcTemplate.execute(sql);
    }
}
