package IntegrationTests.TestsSuite;

import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.DataType;
import IntegrationTests.ObjectUtils.Entities;
import IntegrationTests.RuleSuite.WebServiceRule;
import IntegrationTests.TestData.TestDataForTests;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static IntegrationTests.ConstantsUtils.ConstantValues.COLUMN_NAME_FIELDS_EDITED;
import static IntegrationTests.ConstantsUtils.ConstantValues.LENGTH_FIELDS_EDITED;
import static IntegrationTests.ConstantsUtils.ConstantValues.NAME_FIELDS_EDITED;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by sriznych on 03.11.2016.
 */
public class PutMethod {

    @ClassRule
    public static WebServiceRule ruleWS = new WebServiceRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void createRecord() {
        DBOperations.createRecord2();
    }

    @Test
    public void verifyEditOption() {

        Entities builder = new BaseBuilder().
                BuildName("jjj").
                BuildSchemaName("kjuhy").
                BuildTableName("hjhkj").
              //  BuildFieldList( NAME_FIELDS_EDITED, COLUMN_NAME_FIELDS_EDITED, DataType.NVARCHAR, LENGTH_FIELDS_EDITED).
                build();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //given().contentType(ContentType.JSON).body(gson.toJson(builder)).when().put().then().statusCode(HttpStatus.SC_BAD_REQUEST);
        System.out.println("--->" + gson.toJson(builder));
    }
}
