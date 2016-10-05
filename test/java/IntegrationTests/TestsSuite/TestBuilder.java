package IntegrationTests.TestsSuite;

import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.Entities;
import IntegrationTests.RuleSuite.WebServiceRule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import org.junit.ClassRule;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by sriznych on 17.08.2016.
 */
public class TestBuilder {

    @ClassRule
    public static WebServiceRule rule = new WebServiceRule();

    @Test
    public void builderTest(){
        Entities base = new BaseBuilder().
                BuildId("ENTITIESID").
                BuildName("200").
                BuildSchemaName("111").
                BuildTableName("88").
                build();


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("gson" + gson.toJson(base));
        given().contentType(ContentType.JSON).body(gson.toJson(base)).put().then().statusCode(200);
        System.out.println("gson" + gson.toJson(base));
    }
}
