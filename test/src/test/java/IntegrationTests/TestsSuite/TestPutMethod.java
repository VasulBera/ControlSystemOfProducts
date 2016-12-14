package IntegrationTests.TestsSuite;

import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.HelpUtils.ResponseUtils;
import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.DataType;
import IntegrationTests.ObjectUtils.Entities;
import IntegrationTests.RuleSuite.WebServiceRule;
import IntegrationTests.TestData.TestDataForTests;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-18-07
 *
 * The TestPutMethod class was created for testing HTTP PUT method.
 */

public class TestPutMethod {

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
     * createRecord() method creates record before all tests running
     *
     * @see DBOperations
     */

    @BeforeClass
    public static void createRecord() {
        DBOperations.createFullRecord();
        DBOperations.createRecordIntoEntitiesTable();
    }

    /**
     * The verifyEditOption() test verify HTTP PUT method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP PUT method edit new record and verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method get newly created record
     * - create new object of type Entities(using test data from getValuesForPutMethod() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void verifyEditOption() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName("Name").
                BuildSchemaName("Schema").
                BuildTableName("Table").
                BuildFieldList(NAME_FIELDS_EDITED, COLUMN_NAME_FIELDS_EDITED, DataType.NVARCHAR, LENGTH_FIELDS_EDITED).
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_OK);
        System.out.println("-->" + builder);
        Entities responseForCheckEdit = ResponseUtils.getResponse(ID_VALUE_FOR_VERIFY_METHOD);
        Entities testDataForCheckEdit = TestDataForTests.getValuesForCheckPutMethod();
        errors.checkThat("Response with id " + ID_VALUE_FOR_VERIFY_METHOD + " is incorrect!!!", testDataForCheckEdit.equals(responseForCheckEdit), is(true));
    }

    /**
     * The ediRecordInEntitiesTable() verify HTTP PUT method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP PUT method edit new record and verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method get newly created record
     * - create new object of type Entities(using test data from getValuesFromEntitiesForPutMethod() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void ediRecordInEntitiesTable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       Entities builder = new BaseBuilder().
                BuildName("EditedEntityName").
                BuildSchemaName("EntitySchema").
                BuildTableName("EntityTable").
                build();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_OK);
        Entities response = ResponseUtils.getResponse(ID_VALUE_FIELD_TABLE_FOR_VERIFY_PUT_METHOD);
        Entities testDataOnly = TestDataForTests.getValuesFromEntitiesForPutMethod();
        errors.checkThat("Response and testData is not equal", testDataOnly.equals(response), is(true));
    }

    /**
     * The editRecordWithSpecialSymbols() verify HTTP PUT method.
     * Steps to reproduce:
     * <ol>
     * <li> create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * <li> using HTTP PUT method edit new record and verify that status code is 400((HttpStatus.SC_BAD_REQUEST)
     * <li> using getResponse(String) method get newly created record
     * <li> create new object of type Entities(using test data from getValuesForPutMethod() method for definition field's values)
     * @see TestDataForTests
     * <li> verify that object was not edited
     * @see DBOperations
     * </ol>
     */

  @Test
    public void editRecordWithSpecialSymbols() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName(NAME_ENTITIES_PUT_SC).
                BuildSchemaName(SHEMA_NAME_ENTITIES_PUT_SC).
                BuildTableName(TABLE_NAME_ENTITIES_PUT_SC).
                BuildFieldList(NAME_ENTITIES_PUT_SC, COLUMN_NAME_FIELDS_PUT_SC,
                        TYPE_FIELDS_PUT_SC, LENGTH_FIELDS_PUT_SC).
                build();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(400);
        errors.checkThat("Record was created", DBOperations.isExist(INVALID_ID_FIELD_TABLE_SC_VALUE_FOR_VERIFY_PUT_METHOD, INVALID_ID_ENTITY_TABLE_SC_VALUE_FOR_VERIFY_PUT_METHOD), is(true));
    }

    /**
     * The deleteRecord() method delete all changes after every test
     *
     * @see DBOperations
     */

    @AfterClass
    public static void deleteRecord() {
        DBOperations.deleteRecord(EDITED_ID_VALUE_FOR_VERIFY_PUT_METHOD, ID_VALUE_FOR_VERIFY_METHOD);
        DBOperations.deleteRecord("", ID_VALUE_FIELD_TABLE_FOR_VERIFY_PUT_METHOD);
    }
}
