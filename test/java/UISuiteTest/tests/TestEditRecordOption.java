package UISuiteTest.tests;

import UISuiteTest.LogInData.CreateEntityDataRepository;
import UISuiteTest.LogInData.CreateFieldDataRepository;
import UISuiteTest.LogInData.EditDataRepository;
import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import UISuiteTest.pages.CreateEntityRecordPage;
import UISuiteTest.pages.CreateRecordPageValidation;
import UISuiteTest.pages.EditRecordPage;
import UISuiteTest.pages.HomePage;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import static UISuiteTest.CompareUtils.CompareUtil.CheckListRecord;
import static UISuiteTest.ConstantUtils.constatntValues.idForEntityTableForEdit;
import static UISuiteTest.ConstantUtils.constatntValues.idForFullRecord;
import static UISuiteTest.RuleUtil.LogInRule.homePage;
import static UISuiteTest.SelectDB.selectEntityTableFromAmazonDB;
import static UISuiteTest.SelectDB.selectFullRecordFromAmazonDB;
import static org.hamcrest.core.Is.is;


/**
 * Created by sriznych on 31.08.2016.
 */

public class TestEditRecordOption {

    private CreateRecordPageValidation createRecordPageValidation;
    // private static CreateEntityRecordPage createRecord;

    @ClassRule
    public static SetPropertyBrowserRule ruleProperty = new SetPropertyBrowserRule();

    @ClassRule
    public static OpenBrowserRule ruleBrowser = new OpenBrowserRule();

    @Rule
    public LogInRule ruleLogIn = new LogInRule();

    @Rule
    public ErrorCollector errors = new ErrorCollector();

    @Test
    public void createRecordForDelete() {
        CreateEntityRecordPage createRecord = homePage.createRecordButton().setEntityData(CreateEntityDataRepository.get().getDataCreateEntityForEdit()).saveRecordButton().
                setEntityData(CreateEntityDataRepository.get().getDataCreateFullRecordForDelete()).inputFieldRecord().setFieldData(CreateFieldDataRepository.get().getDataCreateFullRecordForEdit()).
                saveRecordButton();
    }

   // @Test
    public void editEntityTable() {
        EditRecordPage editRecordPage = homePage.gotoEditEntityRecordPage().setIntoEntityEditedData(CreateEntityDataRepository.get().createEntityForEditVerify()).clickSaveEditedRecord();
        HomePage homePage = editRecordPage.goToHomePage();
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getEntityNameList(), "ChangeName"), is(true));
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getSchemaNameList(), "ChangeSchemaName"), is(true));
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getTableNameList(), "ChangeTableName"), is(true));
        errors.checkThat("DATA is not equal", selectEntityTableFromAmazonDB(idForEntityTableForEdit).equals(CreateEntityDataRepository.get().createEntityForEditVerify()), is(true));
        errors.checkThat("DATA is not equal", selectEntityTableFromAmazonDB(idForEntityTableForEdit).equals(CreateEntityDataRepository.get().getDataCreateEntity()), is(false));
    }

   @Test
    public void editFullRecord()  {
     /*  EditRecordPage editRecordPage = homePage.goToEditFullRecord().setFullEditedData(EditDataRepository.get().getDataForEditFullRecord()).clickSaveEditedRecord();
       HomePage homePage = editRecordPage.goToHomePage();
       errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getEntityNameList(), "EditedEntityName"), is(true));
       errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getSchemaNameList(), "EditedSchemaName"), is(true));
       errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getTableNameList(), "EditedTableName"), is(true));*/
       System.out.println("FROM_DB--->" + selectFullRecordFromAmazonDB(idForFullRecord));
       System.out.println("REPO---->" + EditDataRepository.get().getDataForEditFullRecord());
       errors.checkThat("DATA is not equal", selectEntityTableFromAmazonDB(idForFullRecord).equals(EditDataRepository.get().getDataForEditFullRecord()), is(true));
   }
}
