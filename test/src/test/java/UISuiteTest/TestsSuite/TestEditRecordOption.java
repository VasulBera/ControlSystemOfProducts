package UISuiteTest.TestsSuite;

import UISuiteTest.LogInData.*;
import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import UISuiteTest.pages.*;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.testng.annotations.*;

import static UISuiteTest.CompareUtils.CompareUtil.CheckListRecord;
import static UISuiteTest.ConstantUtils.ConstantValues.*;
import static UISuiteTest.RuleUtil.LogInRule.homePage;
import static UISuiteTest.RuleUtil.OpenBrowserRule.application;
import static UISuiteTest.SelectDB.*;
import static org.hamcrest.core.Is.is;

/**
 * Created by sriznych on 31.08.2016.
 */

@RunWith(DataProviderRunner.class)
public class TestEditRecordOption {

    private CreateRecordPageValidation createRecordPageValidation;
    private static HomePage updatedHomePage;
    private EditRecordPageValidation editRecordPageValidation;

    @ClassRule
    public static SetPropertyBrowserRule ruleProperty = new SetPropertyBrowserRule();

    @ClassRule
    public static OpenBrowserRule ruleBrowser = new OpenBrowserRule();

    @Rule
    public LogInRule ruleLogIn = new LogInRule();

    @ClassRule
    public static ErrorCollector errors = new ErrorCollector();

    @BeforeClass
    public static  void createRecordForEditOption() {
        CreateEntityRecordPage createRecord = homePage.createRecordButton().setEntityData(CreateEntityDataRepository.get().getDataCreateEntityForEdit()).saveRecordButton().
                setEntityData(CreateEntityDataRepository.get().getDataCreateFullRecordForDelete()).inputFieldRecord().setFieldData(CreateFieldDataRepository.get().getDataCreateFullRecordForEdit()).
                saveRecordButton().setEntityData(CreateEntityDataRepository.get().dataForIncorrectEntityRecordEdit()).saveRecordButton();
    }

    @Test
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
    public void editFullRecord() {
        EditRecordPage editRecordPage = homePage.goToEditFullRecord().setFullEditedData(EditDataRepository.get().getDataForEditFullRecord()).clickSaveEditedRecord();
        HomePage homePage = editRecordPage.goToHomePage();
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getEntityNameList(), "EditedEntityName"), is(true));
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getSchemaNameList(), "EditedSchemaName"), is(true));
        errors.checkThat("Record was edited incorrect", CheckListRecord(homePage.getTableNameList(), "EditedTableName"), is(true));
        errors.checkThat("DATA is not equal", selectEntityTableFromAmazonDB(idForEditFullRecord).equals(CreateFieldDataRepository.get().getDataCreateFieldFully()), is(false));
    }

    @DataProvider
    public static Object[][] forInvalidEditing() {
        return new Object[][]{
              {CreateEntityDataRepository.get().getDataForSpecialEntity(), ERROR_MESSAGE_FOR_RESERVED_SYMBOLS_EDITING},
              {CreateEntityDataRepository.get().getDataForNumericEntity(), ERROR_MESSAGE_FOR_NUMERIC_SYMBOLS_EDITING },
              {CreateEntityDataRepository.get().getDataForCyrillicEntity(), ERROR_MESSAGE_FOR_CYRILLIC_VALIDATION_EDITING}
        };
    }

    @Test
    @UseDataProvider("forInvalidEditing")
    public void editRecordWithIncorrectData(CreateEntityData entityData, String validationMessage) {
        editRecordPageValidation = homePage.goToEditRecordWithIncorrectData().setDataForIncorrectRecordEdit(entityData).saveEditedInvalidRecordButton();
        errors.checkThat(editRecordPageValidation.getValidationMessage().isDisplayed(), is(true));
        errors.checkThat(editRecordPageValidation.getValidationMessage().getText().equals(validationMessage), is(true));
    }

    @AfterClass
    public static void deleteAllChange() {
        updatedHomePage = application.loadChrome().successLogin(AccountRepository.get().getDataAccount()).gotoHomePage().deleteEditedEntityRecord()
                .confirmDeleteRecord().deleteEditedFullRecord().confirmDeleteRecord();
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getEntityNameList(), "ChangeName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getSchemaNameList(), "ChangeSchemaName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getTableNameList(), "ChangeTableName"), is(false));
        errors.checkThat("Record was not deleted", isExistEntity("ENTITYSCHEMANAMEENTITYTABLENAME"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getEntityNameList(), "EditedEntityName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getSchemaNameList(), "EditedSchemaName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getTableNameList(), "EditedTableName"), is(false));
        errors.checkThat("Record was not deleted", isExistEntity("FULLRECORDSCHEMAFULLRECORDTABLE"), is(false));
    }
}
