package IntegrationTests.TestsSuite;

import IntegrationTests.HelpUtils.ResponseUtils;
import IntegrationTests.ObjectUtils.Entities;
import IntegrationTests.RuleSuite.WebServiceRule;
import IntegrationTests.TestData.TestDataForTests;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.http.HttpStatus;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static IntegrationTests.TestData.TestDataForTests.testData_Id_AdidasShoes;
import static IntegrationTests.TestData.TestDataForTests.testData_Id_BarvinokGoods;
import static com.jayway.restassured.RestAssured.expect;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-17-08
 * The TestGetMethod class was created for testing HTTP method GET.
 */

 @RunWith(DataProviderRunner.class)
 public class TestGetMethod {

    private static final String BARVINOKGOODS_ID = "BARVINOKGOODS";
    private static final String ADIDASSHOES_ID = "ADIDASSHOES";

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
     * The verifyGetMethodWithoutIDs() test verify HTTP GET method
     * Steps to reproduce
     * - using getResponseList() method for getting all records from DB
     *
     * @see ResponseUtils
     * - c reate objectsof type Entities(using testData_Id_AdidasShoes() and testData_Id_BarvinokGoods() methods), generating list of objects
     * @see TestDataForTests
     * - compare lists
     */

    @Test
    public void verifyGetMethodWithoutIDs() {
        List<Entities> ResponseList = ResponseUtils.getResponseList();
        List<Entities> testDataList = new ArrayList(asList(testData_Id_AdidasShoes(), testData_Id_BarvinokGoods()));
        System.out.println("resp--->" + ResponseList);
        System.out.println("testD-->" + testDataList);
        errors.checkThat("Response is not correct", ResponseList.equals(testDataList), is(true));
    }

    /**
     * The verifyGetMethodWithIDs() test verify HTTP GET method
     *
     * @param idValue  represents id value
     * @param testData represents test data
     *                 Steps to reproduce
     *                 - using getResponse(String) method for getting record with (String) id value
     * @see ResponseUtils
     * - generating test data(using testData_Id_AdidasShoes() and testData_Id_BarvinokGoods() methods)
     * @see TestDataForTests
     * - compare test data and response
     */
    @Test
    @UseDataProvider("validID")
    public void verifyGetMethodWithIDs(String idValue, Entities testData) {
        Entities response = ResponseUtils.getResponse(idValue);
        System.out.println("response-->" + response);
        errors.checkThat("Response with id " + idValue + " is incorrect!!!", response.equals(testData), is(true));
    }

    /**
     * The verifyValidID(String) was created for verify correct id values in URL
     * The verifyValidID(String) test use data provider "dataForURL".
     *
     * @param testData represent correct id values
     *steps to reproduce
     *using HTTP GET method and correct id values verify that status code is equals '200' (HttpStatus.SC_OK)
     */

    @Test
    @UseDataProvider("dataForURL")
    public void verifyValidID(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_OK).when().then().get(testData), is(true));
    }

    /**
     * The verifyBaseURL(String) was created for verify base URL
     * The verifyBaseURL(String) test use data provider "emptyIdString".
     *
     * @param testData represent " " (empty String) value
     *Steps to reproduce
     *using HTTP GET method and " " (empty String) as id values verify that status code is equals '200' (HttpStatus.SC_OK)
     */

    @Test
    @UseDataProvider("emptyIdString")
    public void verifyBaseURL(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_OK).when().then().get(testData), is(true));
    }

    /**
     * The verifyAlphabeticalSymbolsForID(String) was created for verify correct id values for URL
     * The verifyAlphabeticalSymbolsForID(String) test use data provider "alphabeticalSymbols".
     *
     * @param testData represent alhabetical symbols ()
     *Steps to reproduce
     *using HTTP GET method and alhabetical symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    @Test
    @UseDataProvider("alphabeticalSymbols")
    public void verifyAlphabeticalSymbolsForID(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_NOT_FOUND).when().get(testData), is(true));
    }

    /**
     * The verifysCyrillicSymbolsForID(String) was created for verify correct id values for URL
     * The verifysCyrillicSymbolsForID(String) test use data provider "cyrillicSymbols".
     *
     * @param testData represent cyrillic symbols from data provider
     *Steps to reproduce
     *using HTTP GET method and cyrillic symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    @Test
    @UseDataProvider("cyrillicSymbols")
    public void verifysCyrillicSymbolsForID(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_NOT_FOUND).when().get(testData), is(true));
    }

    /**
     * The verifyINumericValuesForID(String) was created for verify correct id values for URL
     * The verifysCyrillicSymbolsForID(String) test use data provider "cyrillicSymbols".
     *
     * @param testData represent cyrillic symbols from data provider
     *Steps to reproduce
     *using HTTP GET method and cyrillic symbols as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    @Test
    @UseDataProvider("numberSymbols")
    public void verifyINumericValuesForID(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_NOT_FOUND).when().then().get(testData), is(true));
    }

    /**
     * The verifySpecialCharactersForID(String) was created for verify correct id values for URL
     * The verifySpecialCharactersForID(String) test use data provider "specialCharacters".
     *
     * @param testData represent special characters from data provider.
     *Steps to reproduce
     *using HTTP GET method and special characters as id values verify that status code is equals '400' (HttpStatus.SC_NOT_FOUND)
     */

    @Test
    @UseDataProvider("specialCharacters")
    public void verifySpecialCharactersForID(String testData) {
        errors.checkThat("Status code incorrect", expect().statusCode(HttpStatus.SC_NOT_FOUND).when().then().get(testData), is(true));

    }

    /**
     * The validURL() data provider storage test data, allows to run the same test over and over again using different values.
     * Steps to reproduce
     * create two new objects of type Entities(using test data from testData_Id_AdidasShoes() and testData_Id_BarvinokGoods() methods for definition field's values)
     *
     * @return id value and corresponding object of type Entities()
     * @see TestDataForTests
     */
    @DataProvider
    public static Object[][] validID() {
        return new Object[][]{
                {BARVINOKGOODS_ID, TestDataForTests.testData_Id_BarvinokGoods()},
                {ADIDASSHOES_ID, TestDataForTests.testData_Id_AdidasShoes()}
        };
    }

    /**
     * The dataForURL() data provider storage test data, allows to run verifyValidID(String) test over and over again using different values.
     *
     * @return correct id values
     */

    @DataProvider
    public static Object[][] dataForURL() {
        return new Object[][]{
                {"ADIDASSHOES"},
                {"BARVINOKGOODS"}
        };
    }

    /**
     * The emptyIdString() data provider storage test data, allows to run verifyBaseURL(String) test over and over again using different values.
     *
     * @return correct empty String
     */

    @DataProvider
    public static Object[][] emptyIdString() {
        return new Object[][]{
                {""}
        };
    }

    /**
     * The alphabeticalSymbols() data provider storage test data, allows to run verifyBaseURL(String) test over and over again using different values.
     *
     * @return alhabetical symbols,
     */

    @DataProvider
    public static Object[][] alphabeticalSymbols() {
        return new Object[][]{
                {"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}, {"i"}, {"j"}, {"k"}, {"l"}, {"m"}, {"n"}, {"o"},
                {"p"}, {"q"}, {"r"}, {"s"}, {"t"}, {"u"}, {"v"}, {"w"}, {"x"}, {"y"}, {"z"}
        };
    }

    /**
     * The cyrillicSymbols() data provider storage test data, allows to run verifysCyrillicSymbolsForID(String) test over and over again using different values.
     *
     * @return cyrillic symbols
     */

    @DataProvider
    public static Object[][] cyrillicSymbols() {
        return new Object[][]{
                {"а"}, {"б"}, {"в"}, {"г"}, {"д"}, {"е"}, {"є"}, {"ж"}, {"з"}, {"и"}, {"і"}, {"ї"}, {"й"}, {"к"}, {"л"}, {"м"},
                {"н"}, {"о"}, {"п"}, {"р"}, {"с"}, {"т"}, {"у"}, {"ф"}, {"х"}, {"ц"}, {"ч"}, {"ш"}, {"щ"}, {"ь"}, {"ю"}, {"я"},
        };
    }

    /**
     * The numberSymbols() data provider storage test data, allows to run verifyINumericValuesForID(String) test over and over again using different values.
     *
     * @return number symbols
     */

    @DataProvider
    public static Object[][] numberSymbols() {
        return new Object[][]{
                {"0"}, {"1"}, {"2"}, {"3"}, {"4"}, {"5"}, {"6"}, {"7"}, {"8"}, {"9"}
        };
    }

    /**
     * The specialCharacters() data provider storage test data, allows to run verifySpecialCharactersForID(String) test over and over again using different values.
     *
     * @return special characters symbols
     */

    @DataProvider
    public static Object[][] specialCharacters() {
        return new Object[][]{
                {"!"}, {"."}, {"="}, {"-"}, {"*"}, {"+"}, {"("}, {")"}, {"{"}, {"}"}, {"@"}, {"$"},
                {"%"}, {"^"}, {":"}, {"&"}, {"|"}, {"<"}, {">"}, {";"}
        };
    }












}
