package UISuiteTest.tests;

import UISuiteTest.LogInData.CreateEntityDataRepository;
import UISuiteTest.LogInData.CreateFieldDataRepository;
import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import UISuiteTest.pages.CreateEntityRecordPage;
import UISuiteTest.pages.HomePage;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static UISuiteTest.CompareUtils.CompareUtil.CheckListRecord;
import static UISuiteTest.ConstantUtils.constatntValues.FULL_RECORD_NAME;
import static UISuiteTest.RuleUtil.LogInRule.homePage;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 31.08.2016.
 */

public class TestDeleteOption {

    private CreateEntityRecordPage createEntityRecordPage;

    @ClassRule
    public static SetPropertyBrowserRule ruleProperty = new SetPropertyBrowserRule();

    @ClassRule
    public static OpenBrowserRule ruleBrowser = new OpenBrowserRule();

    @Rule
    public LogInRule ruleLogIn = new LogInRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Before
    public void createRecord() throws InterruptedException {
       createEntityRecordPage = homePage.createRecordButton().setEntityData(CreateEntityDataRepository.get().getDataCreateEntityFully()).inputFieldRecord().
                setFieldData(CreateFieldDataRepository.get().getDataCreateFieldFully()).saveRecordButton();
    }

    @Test
    public void verifyDeleteOnHomePage() {
      /*HomePage changedHomePage = homePage.deleteRecord().confirmDeleteRecord();
        errors.checkThat("Record was not deleted", CheckListRecord(changedHomePage.getEntityNameList(), FULL_RECORD_NAME), is(false));*/
    }

    @Test
    public void verifyDeleteOnCreateEntityRecordPage() {
        /*HomePage changedHomePage = homePage.createRecordButton().deleteRecord().confirmDeleteRecord();
        errors.checkThat("Record was not deleted", CheckListRecord(changedHomePage.getEntityNameList(), FULL_RECORD_NAME), is(false));*/
    }
}
