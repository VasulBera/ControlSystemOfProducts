package IntegrationTests.TestsSuite;

import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.HelpUtils.ResponseUtils;
import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.Entities;
import IntegrationTests.RuleSuite.WebServiceRule;
import IntegrationTests.TestData.TestDataForTests;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import java.sql.SQLException;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-08-08
 * The TestPatchMethod class was created for testing HTTP method PATCH.
 */

public class TestPatchMethod {

    /**
     * The WebServiceRule ruleWS rule generate base URI, base path, base port.
     * URI generated at the beginning of class.
     *
     * @see WebServiceRule
     */

    @ClassRule
    public static WebServiceRule ruleWS = new WebServiceRule();

    /**
     * The ErrorCollector errors rule allows execution of a test to continue after the first problem is found .
     */

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    /**
     * The createRecord() creates record in DB before each test
     *
     * @see DBOperations
     */

    @Test
    public void createRecord() {
        DBOperations.createRecord2();
    }

    /**
     * The checkPatchOptionEntitiesTable() test verify HTTP PATCH method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP PATCH method editting id_entities, name_entities, schema_name_entities and table_nameV fields in newly created object, verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method for getting newly created record
     * - create new object of type Entities(using test data from getValuesForCheckPatchMethodE() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void checkPatchOptionEntitiesTable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName(NAME_ENTITIES_PATCH).
                BuildSchemaName(SHEMA_NAME_ENTITIES_PATCH).
                BuildTableName(TABLE_NAME_ENTITIES_PATCH).
                BuildFieldList( NAME_FIELDS, COLUMN_NAME_FIELDS, TYPE_FIELDS, LENGTH_FIELDS).
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).patch("SCHEMATABLE").then().statusCode(HttpStatus.SC_OK);
        Entities response = ResponseUtils.getResponse("SCHEMATABLE");
        Entities testDataForCheckPatch = TestDataForTests.getValuesForCheckPatchMethodE();
        System.out.println("resp" + response);
        System.out.println("tets" + testDataForCheckPatch);
        errors.checkThat("Response with id " + "SCHEMATABLE" + " is incorrect", testDataForCheckPatch.equals(response), is(true));
    }

    /**
     * The checkPatchOptionFieldsTable() test verify HTTP PATCH method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP PATCH method for editting name_fields, column_name_fields, type_fields and length_fields in newly created object, verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method for getting newly editted object
     * - create new object of type Entities(using test data from getValuesForCheckPatchMethodF() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void checkPatchOptionFieldsTable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName(NAME_ENTITIES).
                BuildSchemaName(SHEMA_NAME_ENTITIES).
                BuildTableName(TABLE_NAME_ENTITIES).
                BuildFieldList( NAME_FIELDS_PATCH, COLUMN_NAME_FIELDS_PATCH, TYPE_FIELDS_PATCH, LENGTH_FIELDS_PATCH).
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).patch("SCHEMATABLE").then().statusCode(HttpStatus.SC_OK);
        Entities response = ResponseUtils.getResponse("SCHEMATABLE");
        Entities testDataForCheckPatch = TestDataForTests.getValuesForCheckPatchMethodF();
        System.out.println("resp" + response);
        System.out.println("tets" + testDataForCheckPatch);
       // errors.checkThat("Response with id " + "SCHEMATABLECOLUMN_NAMEFIELDS" + " is incorrect", testDataForCheckPatch.equals(response), is(true));
    }

    /**
     * The checkInvalidPatchOptionFieldsTable() verify HTTP PATCH method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP PATCH method edit new record and verify that status code is 400((HttpStatus.SC_BAD_REQUEST)
     * - using getResponse(String) method get newly created record
     * - create new object of type Entities(using test data from getValuesForPutMethod() method for definition field's values)
     * @see TestDataForTests
     * - verify that object was not edited
     * @see DBOperations
     */

    @Test
    public void checkInvalidPatchOptionFieldsTable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                //BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES_PATCH_SC).
                BuildSchemaName(SHEMA_NAME_ENTITIES_PATCH_SC).
                BuildTableName(TABLE_NAME_ENTITIES_PATCH_SC).
                BuildFieldList( NAME_FIELDS_PATCH_SC, COLUMN_NAME_FIELDS_PATCH_SC, TYPE_FIELDS_PATCH_SC, LENGTH_FIELDS_PATCH_SC).
                build();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).patch(ID_ENTITIES).then().statusCode(404);
        errors.checkThat("Record with" + ID_ENTITIES + "was created", DBOperations.isExist(ID_ENTITIES, ID_FIELDS_PATCH_SC), is(true));
    }

    /**
     * The deleteRecord() method delete all changes after each test
     *
     * @see DBOperations
     */

  //  @After
    public void deleteRecord() throws SQLException {
        DBOperations.deleteRecord(ID_FIELDS, ID_ENTITIES);
    }
}

