package UISuiteTest.pages;

import UISuiteTest.LogInData.CreateEntityData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 25.08.2016.
 */

public class CreateEntityRecordPage extends HomePage {

    @FindBy(xpath = "//input[contains(@id,'entity-name')]")
    private WebElement entityName;

    @FindBy(xpath = "//input[contains(@id,'entity-schema-name')]")
    private WebElement entitySchemaName;

    @FindBy(xpath = "//input[contains(@id, 'entity-table-name')]" )
    private WebElement entityTableName;

    @FindBy(css = "input[type='button'][value='Create']")
    private WebElement createFieldRecordButton;

    @FindBy(css = "input[type='submit'][value='Save']")
    private WebElement saveRecordButton;

    public CreateEntityRecordPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getEntityName() {
        return this.entityName;
    }

    public WebElement getEntitySchemaName() {
        return this.entitySchemaName;
    }

    public WebElement getEntityTableName() {
        return this.entityTableName;
    }

    public WebElement getSaveRecordButton() {
        return this.saveRecordButton;
    }

    public WebElement getCreateFieldRecordButton() {
        return this.createFieldRecordButton;
    }

    public void clickFieldRecordButton() {
        getCreateFieldRecordButton().click();
    }

    public void clearEntityName() {
        getEntityName().clear();
    }

    public void clearEntitySchemaName() {
        getEntitySchemaName().clear();
    }

    public void clearEntityTableName() {
        getEntityTableName().clear();
    }

    public void clickEntityName() { getEntityName().click(); }

    public void clickEntitySchemaName() {
        getEntitySchemaName().click();
    }

    public void clickEntityTableName() {
        getEntityTableName().click();
    }

    public void setEntityName(String entityName) {
        getEntityName().sendKeys(entityName);
    }

    public void setEntitySchemaName(String schemaName) {
        getEntitySchemaName().sendKeys(schemaName);
    }

    public void setEntityTableName(String tableName) {
        getEntityTableName().sendKeys(tableName);
    }

    public void clickSaveRecordButton() {
        getSaveRecordButton().click();
    }

    public CreateEntityRecordPage saveRecordButton() {
        clickSaveRecordButton();
        return new CreateEntityRecordPage();
    }

    public void createEntityRecord(CreateEntityData createEntityData) {
        clickEntityName();
        clearEntityName();
        setEntityName(createEntityData.getEntityName());
        clickEntitySchemaName();
        clearEntitySchemaName();
        setEntitySchemaName(createEntityData.getEntitySchemaName());
        clickEntityTableName();
        clearEntityTableName();
        setEntityTableName(createEntityData.getEntityTableName());
    }

    public CreateEntityRecordPage setEntityData(CreateEntityData createEntityData) {
        createEntityRecord(createEntityData);
        return new CreateEntityRecordPage();
    }

    public CreateFieldPage inputFieldRecord() {
        clickFieldRecordButton();
        return new CreateFieldPage();
    }

    public CreateRecordPageValidation setInvalidEntityData(CreateEntityData createEntityData){
        createEntityRecord(createEntityData);
        return new CreateRecordPageValidation();
    }
}

