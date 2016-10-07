package com.softserveinc.trainee.test;


import com.softserveinc.trainee.entity.administration.PreviousStateEntity;
import com.softserveinc.trainee.entity.administration.PreviousStateField;
import com.softserveinc.trainee.entity.metadata.Entity;
import com.softserveinc.trainee.entity.metadata.Field;
import com.softserveinc.trainee.entity.metadata.FieldType;
import com.softserveinc.trainee.generator.Impl.TableGeneratorImpl;
import com.softserveinc.trainee.loader.Impl.CsvDataLoader;
import org.junit.*;
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

public class TestTableGeneratorImpl {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    TableGeneratorImpl tableGenerator = new TableGeneratorImpl();

    private static Entity entity;

    private static PreviousStateEntity previousStateEntity;

    @BeforeClass
    public static void createEntity(){
        entity = new Entity();
        entity.setSchemaName("client");
        entity.setTableName("product");
        Field fieldName = new Field();
        fieldName.setId("nameId");
        fieldName.setColumnName("name");
        fieldName.setType(FieldType.NVARCHAR);
        fieldName.setLength(450);
        fieldName.setUnique(true);
        Field fieldPrice = new Field();
        fieldPrice.setId("priceId");
        fieldPrice.setColumnName("price");
        fieldPrice.setType(FieldType.FLOAT);
        fieldPrice.setUnique(false);
        entity.setFieldList(new ArrayList<>(Arrays.asList(fieldName, fieldPrice)));

        previousStateEntity = new PreviousStateEntity();
        previousStateEntity.setTableName("product_edit");
        previousStateEntity.setSchemaName("client_edit");

        PreviousStateField previousStateFieldName = new PreviousStateField();
        previousStateFieldName.setId("nameId");
        previousStateFieldName.setColumnName("name_edit");
        previousStateFieldName.setType(FieldType.NVARCHAR);
        previousStateFieldName.setLength(450);
        previousStateFieldName.setUnique(false);

        PreviousStateField previousStateFieldQuantity = new PreviousStateField();
        previousStateFieldQuantity.setId("quantityId");
        previousStateFieldQuantity.setColumnName("quantity");
        previousStateFieldQuantity.setType(FieldType.INT);
        previousStateFieldQuantity.setUnique(true);
        previousStateEntity.setFieldList(new ArrayList<>(Arrays.asList(previousStateFieldName, previousStateFieldQuantity)));
    }

    @Before
    public void initialize(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTable(){
        Mockito.doNothing().when(jdbcTemplate).execute(anyString());
        tableGenerator.createTable(entity);
    }

    @Test
    public void testUpdateTable(){
    }

    @Test
    public void testGetCreateSqlQuery() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String expected = "IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'client' )\n" +
                "BEGIN\n" +
                "USE CustomTables;\n" +
                "EXEC sp_executesql N'CREATE SCHEMA client;';\n" +
                "END\n" +
                "IF OBJECT_ID (N'CustomTables.client.product', N'U') IS NULL\n" +
                "CREATE TABLE [CustomTables].[client].[product] (\n" +
                "id INT IDENTITY(1,1) PRIMARY KEY, \n" +
                "name NVARCHAR(450) NOT NULL,\n" +
                "price FLOAT NOT NULL\n" +
                ")\n" +
                "\n" +
                "ALTER TABLE [CustomTables].[client].[product]\n" +
                "ADD CONSTRAINT client_product_Unique UNIQUE (name)";

        Method method = TableGeneratorImpl.class.getDeclaredMethod("getCreateSqlQuery", Entity.class);
        method.setAccessible(true);
        String actually = (String) method.invoke(tableGenerator, entity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testDeleteTable() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String tableName = "tablename";
        Mockito.doNothing().when(jdbcTemplate).execute(anyString());

        Method method = TableGeneratorImpl.class.getDeclaredMethod("deleteTable", String.class);
        method.setAccessible(true);
        method.invoke(tableGenerator, tableName);
    }

    @Test
    public void testGetUpdateSqlQuery() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String expected = "USE CustomTables;\n" +
                "IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'client' )\n" +
                "BEGIN\n" +
                "EXEC sp_executesql N'CREATE SCHEMA client;';\n" +
                "END\n" +
                "ALTER SCHEMA client TRANSFER client_edit.product_edit\n" +
                "EXEC sp_rename 'client.product_edit', 'product';\n" +
                "EXEC sp_rename 'client.product.name_edit', 'name', 'COLUMN';\n" +
                "DECLARE @DELETE_SQL VARCHAR(4000);\n" +
                "SET @DELETE_SQL = 'ALTER TABLE [CustomTables].[client].[product] DROP CONSTRAINT |ConstraintName|'\n" +
                "SET @DELETE_SQL = REPLACE(@DELETE_SQL, '|ConstraintName|', (SELECT k.name FROM sys.tables t INNER JOIN sys.key_constraints k ON t.object_id = k.parent_object_id WHERE t.name = 'product' AND k.type = 'UQ')\n" +
                ")\n" +
                "EXEC (@DELETE_SQL)\n" +
                "ALTER TABLE client.product DROP COLUMN quantity;\n" +
                "ALTER TABLE client.product ADD price FLOAT NOT NULL;\n";

        Method method = TableGeneratorImpl.class.getDeclaredMethod("getUpdateSqlQuery", PreviousStateEntity.class, Entity.class);
        method.setAccessible(true);
        String actually = (String) method.invoke(tableGenerator, previousStateEntity, entity);
        Assert.assertEquals(expected, actually);
    }

    @Test
    public void testChangeUniqueFieldSqlQuery() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String expected = "SET XACT_ABORT ON\n" +
                "BEGIN TRANSACTION changeUniqueField\n" +
                "SELECT * INTO [CustomTables].[client].[product_tmp] FROM [CustomTables].[client].[product] WHERE 1 = 2\n" +
                "INSERT INTO [CustomTables].[client].[product_tmp] ( name, price ) SELECT name, price FROM [CustomTables].[client].[product]\n" +
                "DELETE FROM [CustomTables].[client].[product]\n" +
                "DECLARE @SQL VARCHAR(4000);\n" +
                "SET @SQL = 'ALTER TABLE [CustomTables].[client].[product] DROP CONSTRAINT |ConstraintName|'\n" +
                "SET @SQL = REPLACE(@SQL, '|ConstraintName|', (SELECT k.name FROM sys.tables t INNER JOIN sys.key_constraints k ON t.object_id = k.parent_object_id WHERE t.name = 'product' AND k.type = 'UQ')\n" +
                ")\n" +
                "EXEC (@SQL)\n" +
                "ALTER TABLE [CustomTables].[client].[product]\n" +
                "ADD CONSTRAINT product_name_Primary_key UNIQUE (name)\n" +
                "INSERT INTO [CustomTables].[client].[product] ( name, price ) SELECT name, price FROM [CustomTables].[client].[product_tmp]\n" +
                "DROP TABLE [CustomTables].[client].[product_tmp]\n" +
                "COMMIT TRANSACTION changeUniqueField";

        Method method = TableGeneratorImpl.class.getDeclaredMethod("changeUniqueFieldSqlQuery", Entity.class);
        method.setAccessible(true);
        String actually = (String) method.invoke(tableGenerator, entity);
        Assert.assertEquals(expected, actually);
    }

    @AfterClass
    public static void deleteEntiies(){
        entity = null;
        previousStateEntity = null;
    }
}
