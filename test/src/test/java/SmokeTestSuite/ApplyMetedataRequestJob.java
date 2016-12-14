package SmokeTestSuite;

import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.ObjectUtils.BaseBuilder;
import IntegrationTests.ObjectUtils.Entities;
import SmokeTestSuite.AdditionalUtils.CustomJob;
import SmokeTestSuite.AdditionalUtils.CustomJobBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.rules.ErrorCollector;

import static IntegrationTests.ConstantsUtils.ConstantValues.*;
import static IntegrationTests.HelpUtils.DBOperations.getRecordFromCustomTablesDB;
import static IntegrationTests.ObjectUtils.DataType.DATE;
import static SmokeTestSuite.AdditionalUtils.ReadCSVFile.readDataFromCSVFile;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 22.11.2016.
 */

public class ApplyMetedataRequestJob {

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @BeforeClass
   // @Test
    public static void verifyCreateRecordsOption() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Entities builder = new BaseBuilder().
                BuildName(SMOKE_VERIFY_ENTITY_NAME).
                BuildSchemaName(SMOKE_VERIFY_SCHEMA_NAME).
                BuildTableName(SMOKE_VERIFY_TABLE_NAME).
                BuildFieldList(SMOKE_VERIFY_FIELD_NAME, SMOKE_VERIFY_COLUMN_NAME, DATE.NVARCHAR, SMOKE_VERIFY_LENGTH).
                build();
        given().contentType(ContentType.JSON).body(gson.toJson(builder)).post("http://52.34.34.95:8080/service/rest/entity/").then().statusCode(HttpStatus.SC_OK);
        System.out.println("Record created, using before");
    }

    @Test
    public void verifyApplyMetadataRequestJob() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CustomJob customJob = new CustomJobBuilder().
                BuildOwnerName("SolomiiaRiznuchok").
                BuildAim_B("all_entities").
                BuildDescription_B("Apply_metadata").
                build();

        given().contentType(ContentType.JSON).body(gson.toJson(customJob)).post("http://52.34.34.95:8080/service/rest/task/").then().statusCode(HttpStatus.SC_ACCEPTED);
        errors.checkThat("Table NOT exist", DBOperations.isTableExist("EntityTableSmoke"), is(true));
        errors.checkThat("Colunm NOT exist", DBOperations.isColumnExist("EntityTableSmoke", "ColumnSmoke"), is(true));
       }

      //  @Test
        public void verifyUploadMetadataRequestJob() {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            CustomJob customJob = new CustomJobBuilder().
                    BuildOwnerName("SolomiiaRiznuchok").
                    BuildAim_B("all_entities").
                    BuildDescription_B("Upload_data").
                    build();
            given().contentType(ContentType.JSON).body(gson.toJson(customJob)).post("http://52.34.34.95:8080/service/rest/task/").then().statusCode(HttpStatus.SC_ACCEPTED);
            errors.checkThat(getRecordFromCustomTablesDB().equals(readDataFromCSVFile()), is(true));
    }

   // @AfterClass
    public static void deleteAllChanges() {
        DBOperations.deleteRecord("ENTITYSCHEMASMOKEENTITYTABLESMOKECOLUMNSMOKE", "ENTITYSCHEMASMOKEENTITYTABLESMOKE");
        System.out.println("After class run!!!");
    }
}

