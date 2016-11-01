package IntegrationTests.RuleSuite;

import com.jayway.restassured.RestAssured;
import org.junit.rules.ExternalResource;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-12-07
 * The WebServiceRule class generate default base URI, base path, base port.
 */

public class WebServiceRule extends ExternalResource {

    /**
     * The WebServiceRule class generates  base URI, base path, base port
     * */
    @Override
    public void before() {

        String baseHost = System.getProperty("server.host");
        RestAssured.baseURI = (baseHost == null) ? "http://52.34.34.95" : baseHost;

        String port = System.getProperty("server.port");
        RestAssured.port = (port == null) ? 8080 : Integer.valueOf(port);

        String basePath = System.getProperty("server.base");
        RestAssured.basePath = (basePath == null) ? "/service/rest/entity/" : basePath;
    }

    @Override
    public void after() {
        System.out.println(" After WebService rule class!!!");
    }
}