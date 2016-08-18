package com.softserveinc.trainee.test;


import com.softserveinc.trainee.EntityUtil.EntityUtil;
import com.softserveinc.trainee.entity.Entity;
import com.softserveinc.trainee.entity.Field;
import com.softserveinc.trainee.entity.FieldType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEntityUtil {


    @Test
    public void testCreateDb(){
        Entity entity = new Entity();
        entity.setSchemaName("schema_name");
        String expected = "IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = '" + entity.getSchemaName() +  "') CREATE DATABASE " + entity.getSchemaName();
        String actualy = EntityUtil.createDb(entity);
        Assert.assertEquals(expected, actualy);
    }

    @Test
    public void testCreateTable(){
        Entity entity = new Entity();
        entity.setSchemaName("schema_name");
        entity.setTableName("table_name");
        Field field = new Field();
        field.setColumnName("column_name");
        field.setType(FieldType.NVARCHAR);
        field.setLength(45);
        Field field2 = new Field();
        field2.setColumnName("column_name2");
        field2.setType(FieldType.INT);
        List<Field> fields = new ArrayList(Arrays.asList(field, field2));
        entity.setFieldList(fields);
        String expected = "USE schema_name\n" +
                " IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='table_name' AND xtype='U')\n" +
                " CREATE TABLE table_name (\n" +
                " id int  NOT NULL Primary Key IDENTITY(1,1),\n" +
                " column_name NVARCHAR(45),\n" +
                " column_name2 INT\n" +
                " )";
        String actualy = EntityUtil.createTable(entity);
        Assert.assertEquals(expected, actualy);
    }
}
