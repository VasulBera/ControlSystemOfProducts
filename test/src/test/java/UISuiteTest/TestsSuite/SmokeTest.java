package UISuiteTest.TestsSuite;

import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static UISuiteTest.RuleUtil.LogInRule.homePage;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 25.08.2016.
 */

public class SmokeTest {

    @ClassRule
    public static SetPropertyBrowserRule ruleProperty = new SetPropertyBrowserRule();

    @ClassRule
    public static OpenBrowserRule ruleBrowser = new OpenBrowserRule();

    @Rule
    public LogInRule ruleLogIn = new LogInRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void SmokeTest() {
        errors.checkThat("Table is not present", homePage.getTableEntityList().isDisplayed(), is(true));
        errors.checkThat("Title is not right", homePage.getTableTitleText().equals("Entity List"), is(true));
    }
}
