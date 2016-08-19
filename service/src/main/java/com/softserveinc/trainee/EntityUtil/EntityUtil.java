package com.softserveinc.trainee.EntityUtil;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;

public class EntityUtil {

    public static String createDb(Entity entity){
        StringBuilder result = new StringBuilder();
        result.append("IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = '" + entity.getSchemaName() +  "') CREATE DATABASE " + entity.getSchemaName());
        return result.toString();
    }

    public static String createTable(Entity entity){
        StringBuilder result = new StringBuilder();
        result.append("USE " + entity.getSchemaName() + "\n " + "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='" + entity.getTableName() + "' AND xtype='U')" +
                "\n CREATE TABLE " + entity.getTableName() +" (\n id int  NOT NULL Primary Key IDENTITY(1,1)");
        for(Field f: entity.getFieldList()){
            result.append(",\n");
            if(f.getType() == FieldType.NVARCHAR) {
                result.append(" " + f.getColumnName() + " " + f.getType() + "(" + f.getLength() + ")");
            }else{
                result.append(" " + f.getColumnName() + " " + f.getType());
            }
        }
        result.append("\n )");
        return result.toString();
    }
}
