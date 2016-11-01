package IntegrationTests.TestsSuite;

import IntegrationTests.RuleSuite.WebServiceRule;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 01.11.2016.
 */
public class TestGetResp {

    @ClassRule
    public static WebServiceRule ruleWS = new WebServiceRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void verifyValidID() {

        expect().when().get().then().statusCode(HttpStatus.SC_OK);
    }

}
