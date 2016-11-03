package IntegrationTests.TestsSuite;

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
import org.junit.ClassRule;
import org.junit.Test;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static IntegrationTests.ConstantsUtils.ConstantValues.ID_ENTITIES;
import static IntegrationTests.ConstantsUtils.ConstantValues.LENGTH_FIELDS_S;
import static UISuiteTest.ConstantUtils.DataType.dataType.INT;
import static UISuiteTest.TestsSuite.TestCreateRecordOption.errors;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 17.08.2016.
 */
public class TestBuilder {

    @ClassRule
    public static WebServiceRule rule = new WebServiceRule();

    @Test
    public void builderTest(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName("Name5").
                BuildSchemaName("Schema5").
                BuildTableName("Table5").
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(builder)).put("SCHEMATABLE");//.then().statusCode(HttpStatus.SC_OK);
        Entities responseForCheckPost = ResponseUtils.getResponse("SCHEMATABLE");
        System.out.println("-->" + responseForCheckPost);
       // Entities testDataForCheckPost = TestDataForTests.getValuesForCheckPostMethod();
       // errors.checkThat("Response with id " + "SCHEMATABLE" + " is incorrect!!!", responseForCheckPost.equals(testDataForCheckPost), is(true));
    }
}
