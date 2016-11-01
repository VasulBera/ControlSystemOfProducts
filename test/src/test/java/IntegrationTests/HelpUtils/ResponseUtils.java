package IntegrationTests.HelpUtils;

import IntegrationTests.ObjectUtils.Entities;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jayway.restassured.RestAssured.when;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-16-08
 * The ResponseUtils.java class created for definition methods witch convert JSON response to JAVA Object.
 */

public class ResponseUtils {

    private static Response response;
    private static String JSONAsString;

    /**
     * The getResponse(String) method created for extract JSON response to JAVA Object
     * @return return value of type Entities. This value contains JSON response as JAVA Object.
     * @see Entities
     * */

    public static Entities getResponse(String id) {
        Gson gson = new Gson();
        response = when().get(id).then().contentType(ContentType.JSON).extract().response();
        JSONAsString = response.asString();
        Entities responseMap = gson.fromJson(JSONAsString, Entities.class);
        return responseMap;
    }

    /**
     * The getResponseList() method created for extract JSON response to list of JAVA Object
     * @return return list of values of type Entities. This value contains JSON Object as list of JAVA Objects.
     * @see Entities
     * */

    public static List<Entities> getResponseList() {
        Gson gson = new Gson();
        response = when().get().then().contentType(ContentType.JSON).extract().response();
        JSONAsString = response.asString();
        List<Entities> responseList = new ArrayList(Arrays.asList(gson.fromJson(response.asString(), Entities[].class)));
        return responseList;
    }

}
