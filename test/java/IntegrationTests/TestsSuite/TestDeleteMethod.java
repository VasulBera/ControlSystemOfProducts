package IntegrationTests.TestsSuite;


import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.RuleSuite.WebServiceRule;
import IntegrationTests.TestData.TestDataForTests;

import com.jayway.restassured.http.ContentType;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-18-07
 * The TestDeleteMethod class was created for testing HTTP DELETE method.
 */
@RunWith(DataProviderRunner.class)
public class TestDeleteMethod {

    private static final String unExistId = " ";

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
     *
     * @see DBOperations
     */

    @Test
    public void createRecordForTest() {
        DBOperations.createRecord();
    }

    /**
     * The checkDeleteOptionWithExistID() test verify HTTP DELETE method.
     * Steps to reproduce
     * - using HTTP DELETE method delete record created in createRecordForTest() and verify that status code is 204((HttpStatus.SC_NOT_FOUND)
     *
     * @see TestDataForTests
     * - verify that record was deleted and not exist in DB
     * @see DBOperations
     */



    @Test
    public void checkDeleteOptionWithExistID() {
        given().contentType(ContentType.JSON)
                .when().delete(ID_ENTITIES).then().statusCode(204);
        errors.checkThat("Record with" + ID_ENTITIES + " and " + ID_FIELDS + "  Ids was not deleted", DBOperations.isExist(ID_ENTITIES, ID_FIELDS), is(true));
    }

    /**
     * The deleteNONExistenID() test verify HTTP DELETE method.
     * Steps to reproduce
     * - using HTTP DELETE method for deleting record with non-existent id and verify that status code is 204((HttpStatus.SC_NOT_FOUND)
     *
     * @see TestDataForTests
     * - verify that no one record was not deleted
     * @see DBOperations
     */

    @Test
    @UseDataProvider("nonExistentID")
    public void deleteNONExistenID(String idFields, String idEntities) {
        given().contentType(ContentType.JSON)
                .when().delete(idFields).then().statusCode(HttpStatus.SC_NOT_FOUND);
        errors.checkThat("Record was not deleted", DBOperations.isExist(idFields, idEntities), is(true));
    }

    /**
     * The nonExistentID() data provider storage test data, allows to run deleteNONExistenID(String) test over and over again using different values.
     *
     * @return non-existen  ids values
     */

    @DataProvider
    public static Object[][] nonExistentID() {
        return new Object[][]{
                {" ", " "}, {"***", "---"}, {"FAIL_ID", "FAIL_ID"},
                {"FAIL_ENTITIES_ID", "FAIL_FIELDS_ID"},
                {"111", "222"}
        };
    }

    @Test
    @UseDataProvider("fields")
    public void deleteWITHNOTID(String idFields, String idEntities) {
        given().contentType(ContentType.JSON)
                .when().delete(idFields).then().statusCode(HttpStatus.SC_NOT_FOUND);
        errors.checkThat("Record was not deleted", DBOperations.isExist(idFields, idEntities), is(true));
    }

    /**
     * The nonExistentID() data provider storage test data, allows to run deleteNONExistenID(String) test over and over again using different values.
     *
     * @return non-existen  ids values
     */

    @DataProvider
    public static Object[][] fields() {
        return new Object[][]{
                {NAME_ENTITIES, ID_ENTITIES},
                {SHEMA_NAME_ENTITIES, ID_ENTITIES},
                {TABLE_NAME_ENTITIES, ID_ENTITIES},
                {ID_FIELDS, ID_ENTITIES},
                {NAME_FIELDS, ID_ENTITIES},
                {TABLE_NAME_ENTITIES, ID_ENTITIES},
                {COLUMN_NAME_FIELDS, ID_ENTITIES}
        };
    }
}

