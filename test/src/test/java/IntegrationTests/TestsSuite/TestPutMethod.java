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
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-18-07
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
     * createRecord() creates record before every tests
     * @see DBOperations
     * */

   // @Before
    @Test
    public void createRecord() {
        DBOperations.createRecord();
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
              //  BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES_EDITED).
                BuildSchemaName(SHEMA_NAME_ENTITIES_EDITED).
                BuildTableName(TABLE_NAME_ENTITIES_EDITED).
                BuildFieldList( NAME_FIELDS_EDITED, COLUMN_NAME_FIELDS_EDITED, TYPE_FIELDS_EDITED, LENGTH_FIELDS_EDITED).
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_OK);
        Entities responseForCheckEdit = ResponseUtils.getResponse(ID_ENTITIES);
        Entities testDataForCheckEdit = TestDataForTests.getValuesForCheckPutMethod();
        System.out.println("resp" + responseForCheckEdit);
        System.out.println("test" + testDataForCheckEdit);
        errors.checkThat("Response with id " + ID_ENTITIES + " is incorrect!!!", testDataForCheckEdit.equals(responseForCheckEdit), is(true));
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
        Entities builder = new BaseBuilder().
               // BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES_EDITED_ONLY).
                BuildSchemaName(SHEMA_NAME_ENTITIES_EDITED_ONLY).
                BuildTableName(TABLE_NAME_ENTITIES_EDITED_ONLY).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_OK);
        Entities response = ResponseUtils.getResponse(ID_ENTITIES);
        Entities testDataOnly = TestDataForTests.getValuesFromEntitiesForPutMethod();
       // errors.checkThat("Response and testData is not equal" + testDataOnly.equals(response), is(true));
    }

    /**
     * The editRecordWithSpecialSymbols() verify HTTP PUT method.
     * Steps to reproduce:
     * <ol>
     * <li> create new object of type Entities(using ConstantValues class for definition field's values)
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * <li> using HTTP PUT method edit new record and verify that status code is 400((HttpStatus.SC_BAD_REQUEST)
     * <li> using getResponse(String) method get newly created record
     * <li> create new object of type Entities(using test data from getValuesForPutMethod() method for definition field's values)
     * @see TestDataForTests
     * <li> verify that object was not edited
     * @see DBOperations
     * </ol>
     *
     */

    @Test
    public void editRecordWithSpecialSymbols() {
        Entities builder = new BaseBuilder().
               // BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES_PUT_SC).
                BuildSchemaName(SHEMA_NAME_ENTITIES_PUT_SC).
                BuildTableName(TABLE_NAME_ENTITIES_PUT_SC).
                BuildFieldList( NAME_ENTITIES_PUT_SC, COLUMN_NAME_FIELDS_PUT_SC,
                        TYPE_FIELDS_PUT_SC, LENGTH_FIELDS_PUT_SC).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_BAD_REQUEST);
        errors.checkThat("Record with" + ID_ENTITIES + "was created", DBOperations.isExist(ID_FIELDS_PUT_SC, ID_ENTITIES), is(true));
    }

    /**
     * The deleteRecord() method delete all changes after every test
     * @see DBOperations
     * */

   //@After
    @Test
    public void deleteRecord() {
       DBOperations.deleteRecord(ID_FIELDS, ID_ENTITIES);
    }
}
