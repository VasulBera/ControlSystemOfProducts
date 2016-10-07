package com.softserveinc.trainee.test;

import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.generator.TableGenerator;
import com.softserveinc.trainee.loader.DataSource;
import com.softserveinc.trainee.loader.Impl.CsvDataLoader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Matchers.anyString;

public class TestCsvDataLoader {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    TableGenerator tableGenerator;

    @Mock
    DataSource dataSource;

    @InjectMocks
    CsvDataLoader csvDataLoader = new CsvDataLoader();

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteAllRecord() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Entity entity = new Entity();
        Mockito.doNothing().when(jdbcTemplate).execute(anyString());
        Method method = CsvDataLoader.class.getDeclaredMethod("deleteAllRecord", Entity.class);
        method.setAccessible(true);
        method.invoke(csvDataLoader, entity);
    }

    @Test
    public void testLoadRemainderData() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Entity entity = new Entity();
        entity.setTableName("product");
        entity.setSchemaName("client");
        Field field = new Field();
        field.setColumnName("price");
        entity.setFieldList(new ArrayList<>(Arrays.asList(field)));
        Mockito.doNothing().when(jdbcTemplate).execute(anyString());
        Method method = CsvDataLoader.class.getDeclaredMethod("loadRemainderData", Entity.class, Entity.class);
        method.setAccessible(true);
        method.invoke(csvDataLoader, entity, entity.getTmpEntity());
    }
}
