package UISuiteTest.TestsSuite;

import UISuiteTest.LogInData.*;
import UISuiteTest.RuleUtil.LogInRule;
import UISuiteTest.RuleUtil.OpenBrowserRule;
import UISuiteTest.RuleUtil.SetPropertyBrowserRule;
import UISuiteTest.pages.CreateEntityRecordPage;
import UISuiteTest.pages.CreateRecordPageValidation;
import UISuiteTest.pages.HomePage;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

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
public class TestCreateRecordOption {

    private CreateRecordPageValidation createRecordPageValidation;
    private CreateEntityRecordPage createRecord;
    private static HomePage updatedHomePage;

    @ClassRule
    public static SetPropertyBrowserRule ruleProperty = new SetPropertyBrowserRule();

    @ClassRule
    public static OpenBrowserRule ruleBrowser = new OpenBrowserRule();

    @Rule
    public LogInRule ruleLogIn = new LogInRule();

    @ClassRule
    public static ErrorCollector errors = new ErrorCollector();

  //  @Test
    public void createEntityRecord() {
        createRecord = homePage.createRecordButton().setEntityData(CreateEntityDataRepository.get().getDataCreateEntity()).saveRecordButton();
        errors.checkThat("Record was not create", CheckListRecord(homePage.getEntityNameList(), "EntityName"), is(true));
        errors.checkThat("Record was not created", CheckListRecord(homePage.getSchemaNameList(), "EntitySchemaName"), is(true));
        errors.checkThat("Record was not created", CheckListRecord(homePage.getTableNameList(), "EntityTableName"), is(true));
        System.out.println("FROM DB-->" + selectEntityTableFromAmazonDB("ENTITYSCHEMANAMEENTITYTABLENAME"));
        errors.checkThat("Data is not equal",
                selectEntityTableFromAmazonDB("ENTITYSCHEMANAMEENTITYTABLENAME").equals(CreateEntityDataRepository.get().getDataCreateEntity()), is(true));
    }

   @Test
    public void createFullRecord() {
        createRecord = homePage.createRecordButton().
                setEntityData(CreateEntityDataRepository.get().getDataCreateEntityFully()).inputFieldRecord().setFieldData(CreateFieldDataRepository.get().getDataCreateFieldFully()).
                saveRecordButton();
        errors.checkThat("Record was not create", CheckListRecord(homePage.getEntityNameList(), "FullRecordName"), is(true));
        errors.checkThat("Record was not created", CheckListRecord(homePage.getSchemaNameList(), "FullRecordSchema"), is(true));
        errors.checkThat("Record was not created", CheckListRecord(homePage.getTableNameList(), "FullRecordTable"), is(true));
        System.out.println("FROM DB-->" + selectFullRecordFromAmazonDB("FULLRECORDSCHEMAFULLRECORDTABLE"));
        System.out.println("REPO--->" + DataForRepositoryDB.get().getDataForDBCheck());
        errors.checkThat("Data is not equal", selectFullRecordFromAmazonDB("FULLRECORDSCHEMAFULLRECORDTABLE").equals(DataForRepositoryDB.get().getDataForDBCheck()), is(true));
    }

    @Test
    public void verifyMaxLengthOfValue() throws InterruptedException {
        createRecordPageValidation = homePage.createRecordButton().
                setInvalidEntityData(CreateEntityDataRepository.get().getDataForCreateFieldInvalidLength()).inputInvalidField().setInvalidFieldData(CreateFieldDataRepository.get().getDataForCreateFieldInvalidLength()).
                saveInvalidRecordButton();
        errors.checkThat(createRecordPageValidation.getValidationMessageText().equals(ERROR_MESSAGE_FOR_TOO_MUCH_LENGTH), is(false));
    }

    @DataProvider
    public static Object[][] forValidationMessage() {
        return new Object[][]{
                {CreateEntityDataRepository.get().getDataForEmptyEntity(), CreateFieldDataRepository.get().getDataForCreateEmptyField(), ERROR_MESSAGE_FOR_EMPTY_VALIDATION, EMPTY_FIELDS},
                {CreateEntityDataRepository.get().getDataForSpecialEntity(), CreateFieldDataRepository.get().getDataForCreateSpecialSymbolsField(), ERROR_MESSAGE_FOR_RESERVED_SYMBOLS, SPECIAL_SYMBOLS},
                {CreateEntityDataRepository.get().getDataForNumericEntity(), CreateFieldDataRepository.get().getDataForCreateNumericField(), ERROR_MESSAGE_FOR_NUMERIC_SYMBOLS, NUMERIC_SYMBOLS},
                {CreateEntityDataRepository.get().getDataForCyrillicEntity(), CreateFieldDataRepository.get().getDataForCreateCyrillicField(), ERROR_MESSAGE_FOR_CYRILLIC_VALIDATION, CYRILLIC_SYMBOLS},
        };
    }

    @Test
    @UseDataProvider("forValidationMessage")
    public void forValidation(CreateEntityData entityData, CreateFieldData createFieldData, String validationMessage, String idRecord)   {
        createRecordPageValidation = homePage.createRecordButton().
                setInvalidEntityData(entityData).inputInvalidField().setInvalidFieldData(createFieldData).saveInvalidRecordButton();
        errors.checkThat(createRecordPageValidation.getValidationMessage().isDisplayed(), is(true));
        errors.checkThat(createRecordPageValidation.getValidationMessageText().equals(validationMessage), is(true));
        errors.checkThat("Record was created", CheckListRecord(homePage.getEntityNameList(), idRecord), is(false));
    }

    @AfterClass
    public static  void deleteAllChange() {
        updatedHomePage = application.loadChrome().successLogin(AccountRepository.get().getDataAccount()).gotoHomePage().deleteFullRecordLink().confirmDeleteRecord();
        /*errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getEntityNameList(), "EntityName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getSchemaNameList(), "EntitySchemaName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getTableNameList(), "EntityTableName"), is(false));
        errors.checkThat("Record was not deleted", isExistEntity("ENTITYSCHEMANAMEENTITYTABLENAME"), is(false));*/
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getEntityNameList(), "FullRecordName"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getSchemaNameList(), "FullRecordSchema"), is(false));
        errors.checkThat("Record was not deleted", CheckListRecord(updatedHomePage.getTableNameList(), "FullRecordTable"), is(false));
        errors.checkThat("Record was not deleted", isExistEntity("FULLRECORDSCHEMAFULLRECORDTABLE"), is(false));
    }
}
