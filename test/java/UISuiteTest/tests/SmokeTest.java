package UISuiteTest.tests;

import UISuiteTest.ApplicationUtil.Application;
import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import UISuiteTest.pages.CreateEntityRecordPage;
import UISuiteTest.pages.EditRecordPage;
import UISuiteTest.pages.HomePage;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 25.08.2016.
 */

public class SmokeTest {

    private static Application application;
    private HomePage homePage;
    private CreateEntityRecordPage createEntityRecordPage;
    private EditRecordPage editRecordPage;

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
//        errors.checkThat("Title is not right", homePage.getTitleText().equals("Entity List"), is(true));
    }
}
