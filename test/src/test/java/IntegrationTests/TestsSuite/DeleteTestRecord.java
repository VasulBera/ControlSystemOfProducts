package IntegrationTests.TestsSuite;

import IntegrationTests.HelpUtils.DBOperations;
import IntegrationTests.RuleSuite.WebServiceRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * Created by sriznych on 02.11.2016.
 */
public class DeleteTestRecord {

    @ClassRule
    public static WebServiceRule ruleWS = new WebServiceRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void deleteAllChanges() {
        DBOperations.deleteRecord("SCHEMANAMEENTITIESTABLENAMEENTITIESCOLUMN_NAMEFIELDS", "SCHEMANAMEENTITIESTABLENAMEENTITIES");
    }
}
