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
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static IntegrationTests.ConstantsUtils.RandomConstantValues.*;
import static IntegrationTests.ObjectUtils.DataType.INT;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-14-07
 * The TestPostMethod class was created for testing HTTP method POST.
 */

public class TestPostMethod {

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
     * The verifyCreateRecordsOption() verify HTTP POST method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP POST method create new record and verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method get newly created record with id 'ID_ENTITIES'
     * - create new object of type Entities(using test data from getValuesForPostMethod() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void verifyCreateRecordsOption() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
               // BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES).
                BuildSchemaName(SHEMA_NAME_ENTITIES).
                BuildTableName(TABLE_NAME_ENTITIES).
                BuildFieldList( NAME_FIELDS, COLUMN_NAME_FIELDS, TYPE_FIELDS, LENGTH_FIELDS).
                BuildFieldList( NAME_FIELDS_S, COLUMN_NAME_FIELDS_S, TYPE_FIELDS_S, LENGTH_FIELDS_S).
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).post().then().statusCode(HttpStatus.SC_OK);
        Entities responseForCheckPost = ResponseUtils.getResponse(ID_ENTITIES);
        Entities testDataForCheckPost = TestDataForTests.getValuesForCheckPostMethod();
        errors.checkThat("Response with id " + ID_ENTITIES + " is incorrect!!!", responseForCheckPost.equals(testDataForCheckPost), is(true));
    }

    /**
     * The checkCreateRecordsOption() verify HTTP POST method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP POST method create new record and verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method get newly created record with id 'ID_ENTITIES'
     * - create new object of type Entities(using test data from getValuesForPostMethod() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void createRecordOnlyInEntitiesTable() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
               // BuildId(ID_ENTITIES).
                BuildName(NAME_ENTITIES_ONLY).
                BuildSchemaName(SHEMA_NAME_ENTITIES_ONLY).
                BuildTableName(TABLE_NAME_ENTITIES_ONLY).
                build();
        System.out.println("builder-->" + builder);

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).post().then().statusCode(HttpStatus.SC_OK);
        System.out.println("resp" + gson.toJson(builder));
      /*  Entities responseFromEntities = ResponseUtils.getResponse(ID_ENTITIES);
        Entities testDataForEntities = TestTestDAta.getValuesFromEntitiesForPutMethod();
        System.out.println("resp" + responseFromEntities);
        System.out.println("test" + testDataForEntities);
        errors.checkThat("Response with id " + ID_ENTITIES_ONLY + " is incorrect", responseFromEntities.equals(testDataForEntities), is(true));*/
    }

    /**
     * The verifyValidMaxLength() verify HTTP POST method.
     * Steps to reproduce
     * - create new object of type Entities(using RandomConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.RandomConstantValues
     * - using HTTP POST method create new record and verify that status code is 200((HttpStatus.SC_OK)
     * - using getResponse(String) method to get newly created record
     * - create new object of type Entities(using test data from getValuesForVerifyMaxFieldLength() method for definition field's values)
     * @see TestDataForTests
     * - verify objects
     */

    @Test
    public void verifyValidMaxLength() {
        Entities base = new BaseBuilder().
                //BuildId(ID_ETITIES_RANDOM_VALID).
                BuildName(NAME_ETITIES_RANDOM_VALID).
                BuildSchemaName(SHEMA_NAME_ETITIES_RANDOM_VALID).
                BuildTableName(TABLE_NAME_ETITIES_RANDOM_VALID).
                BuildFieldList( NAME_FIELDS_RANDOM_VALID, COLUMN_NAME_FIELDS_RANDOM_VALID, INT, LENGTH_FIELDS_RANDOM_VALID).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        given().contentType(ContentType.JSON).body(gson.toJson(base)).post().then().statusCode(HttpStatus.SC_OK);
        Entities responseValid = ResponseUtils.getResponse(ID_ETITIES_RANDOM_VALID);
        Entities testDataForCheckValid = TestDataForTests.getValuesForVerifyMaxFieldLength();
        errors.checkThat(responseValid.equals(testDataForCheckValid), is(true));
    }

    /**
     * The verifyInValidMaxLength() verify HTTP POST method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP POST method create new record and verify that status code is 400((HttpStatus.SC_BAD_REQUEST)
     * - verify that object was not exist
     */

    @Test
    public void verifyInValidMaxLength() {
        Entities base = new BaseBuilder().
                //BuildId(ID_ETITIES_INVALID).
                BuildName(NAME_ETITIES_INVALID).
                BuildSchemaName(SHEMA_NAME_ETITIES_INVALID).
                BuildTableName(TABLE_NAME_ETITIES_INVALID).
                BuildFieldList(NAME_FIELDS_INVALID, COLUMN_NAME_FIELDS_INVALID, INT, LENGTH_FIELDS_NVALID).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        given().contentType(ContentType.JSON).body(gson.toJson(base)).post().then().statusCode(HttpStatus.SC_BAD_REQUEST);
       // errors.checkThat("Record with" + ID_ETITIES_INVALID + " was created", DBOperations.isExist(ID_ETITIES_INVALID), is(true));
    }

    /**
     * The createRecordWithSpecialCharacters() verify HTTP POST method.
     * Steps to reproduce
     * - create new object of type Entities(using ConstantValues class for definition field's values)
     *
     * @see Entities
     * @see IntegrationTests.ConstantsUtils.ConstantValues
     * - using HTTP POST method create new record and verify that status code is 400((HttpStatus.SC_BAD_REQUEST)
     * - verify that object was not exist
     */

    @Test
    public void createRecordWithSpecialCharacters() {
        Entities base = new BaseBuilder().
               // BuildId(ID_ENTITIES_SC).
                BuildName(NAME_ENTITIES_SC).
                BuildSchemaName(SHEMA_NAME_ENTITIES_SC).
                BuildTableName(TABLE_NAME_ENTITIES_SC).
                BuildFieldList(NAME_FIELDS_SC, COLUMN_NAME_FIELDS_SC, TYPE_FIELDS_SC, LENGTH_FIELDS_SC).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        given().contentType(ContentType.JSON).body(gson.toJson(base)).post().then().statusCode(HttpStatus.SC_BAD_REQUEST);
       // errors.checkThat("Record with" + ID_ENTITIES_SC + "was created", DBOperations.isExist(ID_ENTITIES_SC), is(true));
    }

    /**
     * The deleteAllChanges() method delete all records, which were created in tests
     *
     * @see DBOperations
     */

    @AfterClass
    public static void deleteAllChanges() {
     /*   DBOperations.deleteRecord("TESTIDINTERNAL_NAME", "TESTID");
        DBOperations.deleteRecord((ID_ETITIES_RANDOM_VALID + NAME_FIELDS_RANDOM_VALID).toUpperCase(), ID_ETITIES_RANDOM_VALID);*/

    }
}
