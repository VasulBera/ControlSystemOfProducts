package com.softserveinc.trainee.test;


import com.softserveinc.trainee.dao.CustomTableDao;
import com.softserveinc.trainee.dao.Impl.CustomTableDaoImpl;
import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class TestCustomTableDaoImpl {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    CustomTableDaoImpl customTableDao;

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateTable(){
        Entity entity = new Entity();
        entity.setTableName("PRODUCT");
        entity.setSchemaName("client");
        Field field = new Field();
        field.setColumnName("Price");
        field.setId("PRICE");
        entity.setFieldList(new ArrayList<>(Arrays.asList(field)));
        customTableDao.generateTable(entity);
    }

    @Test
    public void testUpdateTable(){
        Entity entity = new Entity();
        entity.setTableName("PRODUCT");
        entity.setSchemaName("client");
        Field field = new Field();
        field.setColumnName("Price");
        field.setId("PRICE");
        entity.setFieldList(new ArrayList<>(Arrays.asList(field)));
        PreviousStateEntity previousStateEntity = entity.createPreviousStateEntity();
        customTableDao.updateTable(previousStateEntity,entity);
    }

    @Test
    public void testDeleteAllRecord(){
        Entity entity = new Entity();
        entity.setTableName("PRODUCT");
        entity.setSchemaName("client");
        customTableDao.deleteAllRecord(entity);
    }
}
