package UISuiteTest.pages;

import UISuiteTest.LogInData.CreateEntityData;
import UISuiteTest.LogInData.EditData;
import UISuiteTest.LogInData.EditDataRepository;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 29.08.2016.
 */

public class EditRecordPage extends HomePage {

    //============================================================================
    @FindBy(css = "input[id*= 'entity-name'][value = 'EntityNameForEdit']")
    private WebElement entityNameEdit;

    @FindBy(css = "input[id*= 'entity-schema-name'][value = 'EntitySchemaNameForEdit']")
    private WebElement entitySchemaNameEdit;

    @FindBy(css = "input[id*= 'entity-table-name'][value = 'EntityTableNameForEdit']")
    private WebElement entityTableNameEdit;

    //============================FULL_RECORD======================================
    @FindBy(css = "input[id*= 'entity-name'][value = 'FullEntityNameForEdit']")
    private WebElement entityNameForFullRecord;

    @FindBy(css = "input[id*= 'entity-schema-name'][value = 'FullEntitySchemaNameForEdit']")
    private WebElement entitySchemaNameForFullRecord;

    @FindBy(css = "input[id*= 'entity-table-name'][value = 'FullTableNameForEdit']")
    private WebElement entityTableNameForFullRecord;

    @FindBy(xpath = "//span[contains(@id, fieldTable) and text() = 'FullFieldNameForEdit']")
    private WebElement fieldNameForFullRecord;

    @FindBy(xpath = "//span[contains(@id, fieldTable) and text() = 'FullFieldColumnForEdit']")
    private WebElement fieldColumnNameForFullRecord;

    @FindBy(css = "input[id*= 'fieldTable'][value = '700']")
    private WebElement fieldLengthForFullRecord;
//=================================================================================

    @FindBy(css = "input[class = 'btn'][value= 'Save']")
    private WebElement saveEditedRecord;

    @FindBy(css = "input[id*= 'fieldTable'][class='btn']")
    private WebElement addRecordButton;

    @FindBy(css = "span[id*='ilecell'][ class= 'inlineEditWrite']")
    private List<WebElement> listInvisibleFields;

    @FindBy(css = "input[id*='field-length']")
    private List<WebElement> listFieldLength;

    @FindBy(css = "a[title='Entity List Tab']")
    private WebElement entityListButton;

    @FindBy(css = "div[id*='ileinneredit']")
    private WebElement helpBox;

    /*public EditRecordPage() {
        PageFactory.initElements(driver, this);
    }*/

    public EditRecordPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }


    public WebElement getEntityListButton() {
        return this.entityListButton;
    }

    public WebElement getHelpBox() {
        return this.helpBox;
    }

    public EditRecordPage clickHelpBox() {
        getHelpBox().click();
        return new EditRecordPage();
    }

    public HomePage goToHomePage() {
        getEntityListButton().click();
        return new HomePage();
    }

    public WebElement getEntityNameForFullRecord() {
        return this.entityNameForFullRecord;
    }

    public WebElement getEntitySchemaNameForFullRecord() {
        return this.entitySchemaNameForFullRecord;
    }

    public WebElement getEntityTableNameForFullRecord() {
        return this.entityTableNameForFullRecord;
    }

    public WebElement getFieldNameForFullRecord() {
        return this.fieldNameForFullRecord;
    }

    public WebElement getFieldColumnNameForFullRecord() {
        return this.fieldColumnNameForFullRecord;
    }

    public WebElement getFieldLengthForFullRecord() {
        return this.fieldLengthForFullRecord;
    }

    //=============================================Invisible=====================================================
    public WebElement getLength() {
        return listFieldLength.get(listFieldLength.size() - 1);
    }

    public WebElement getFieldNameForEdit() {
        return listInvisibleFields.get(listInvisibleFields.size() - 2);
    }

    public WebElement getColumnNameForEdit() {
        return listInvisibleFields.get(listInvisibleFields.size() - 1);
    }

    public WebElement getSaveEditedRecord() {
        return this.saveEditedRecord;
    }
    //=============================================================================================================

    public EditRecordPage clickSaveEditedRecord() {
        getSaveEditedRecord().click();
        return new EditRecordPage();
    }

    public WebElement getAddRecordButton() {
        return addRecordButton;
    }

    public EditRecordPage addNewField() {
        getAddRecordButton().click();
        return new EditRecordPage();
    }

    public WebElement getEntityNameEdit() {
        return this.entityNameEdit;
    }

    public WebElement getEntitySchemaNameEdit() {
        return this.entitySchemaNameEdit;
    }

    public WebElement getEntityTableNameEdit() {
        return this.entityTableNameEdit;
    }

    public void clickEntityNameEdit() {
        getEntityNameEdit().click();
    }

    public void clearEntityNameEdit() {
        getEntityNameEdit().clear();
    }

    public void clickEntitySchemaNameEdit() {
        getEntitySchemaNameEdit().click();
    }

    public void clearEntitySchemaNameEdit() {
        getEntitySchemaNameEdit().clear();
    }

    public void clickEntityTableNameEdit() {
        getEntityTableNameEdit().click();
    }

    public void clearEntityTableNameEdit() {
        getEntityTableNameEdit().clear();
    }

    public void clickEntityNameForFullRecord() {
        getEntityNameForFullRecord().click();
    }

    public void clearEntityNameForFullRecord() {
        getEntityNameForFullRecord().clear();
    }

    public void clickEntitySchemaNameForFullRecord() {
        getEntitySchemaNameForFullRecord().click();
    }

    public void clearEntitySchemaNameForFullRecord() {
        getEntitySchemaNameForFullRecord().clear();
    }

    public void clickEntityTableNameForFullRecord() {
        getEntityTableNameForFullRecord().click();
    }

    public void clearEntityTableNameForFullRecord() {
        getEntityTableNameForFullRecord().clear();
    }

    public void clickFieldNameForFullRecord() {
        getFieldNameForFullRecord().click();
    }

    public void clearFieldNameForFullRecord() {
        getFieldNameForFullRecord().clear();
    }

    public void clickFieldColumnNameForFullRecord() {
        getFieldColumnNameForFullRecord().click();
    }

    public void clearFieldColumnNameForFullRecord() {
        getFieldColumnNameForFullRecord().clear();
    }

    public void clickFieldLengthValueForFullRecord() {
        getFieldLengthForFullRecord().click();
    }

    public void clearFieldLengthValueForFullRecord() {
        getFieldLengthForFullRecord().clear();
    }

    public void setEntityNameEdit(String entityNameEdit) {
        getEntityNameEdit().sendKeys(entityNameEdit);
    }

    public void setSchemaNameEdit(String entitySchemaNameEdit) {
        getEntitySchemaNameEdit().sendKeys(entitySchemaNameEdit);
    }

    public void setEntityTableNameEdit(String entityTableNameEdit) {
        getEntityTableNameEdit().sendKeys(entityTableNameEdit);
    }

    public void setEntityNameForFullRecord(String entityNameForFullRecord) {
        getEntityNameForFullRecord().sendKeys(entityNameForFullRecord);
    }

    public void setEntitySchemaNameForFullRecord(String entitySchemaNameForFullRecord) {
        getEntitySchemaNameForFullRecord().sendKeys(entitySchemaNameForFullRecord);
    }

    public void setEntityTableNameForFullRecord(String entityTableNameForFullRecord) {
        getEntityTableNameForFullRecord().sendKeys(entityTableNameForFullRecord);
    }

    public void setFieldNameForFullRecord(String fieldNameEdit) {
        getFieldNameForFullRecord().sendKeys(fieldNameEdit);
    }

    public void setFieldColumnNameForFullRecord(String fieldColumnNameEdit) {
        getFieldColumnNameForFullRecord().sendKeys(fieldColumnNameEdit);
    }

    public void setFieldLengthValueForFullRecord(String fieldLengthEdit) {
        getFieldLengthForFullRecord().sendKeys(fieldLengthEdit);
    }

    public void inputFullRecord(EditData editData) {
        clickEntityNameForFullRecord();
        clearEntityNameForFullRecord();
        setEntityNameForFullRecord(editData.getEntityNameEdit());
        clickEntitySchemaNameForFullRecord();
        clearEntitySchemaNameForFullRecord();
        setEntitySchemaNameForFullRecord(editData.getEntitySchemaNameEdit());
        clickEntityTableNameForFullRecord();
        clearEntityTableNameForFullRecord();
        setEntityTableNameForFullRecord(editData.getEntityTableNameEdit());

        Actions actions = new Actions(driver);
        actions.moveToElement(getFieldNameForFullRecord()).click().sendKeys("").sendKeys("").sendKeys(EditDataRepository.get().getDataForEditFullRecord().getFieldNameEdit()).perform();
        actions.moveToElement(getFieldColumnNameForFullRecord()).click().sendKeys("").sendKeys("").sendKeys(EditDataRepository.get().getDataForEditFullRecord().getFieldColumnNameEdit()).perform();

        clickFieldLengthValueForFullRecord();
        clearFieldLengthValueForFullRecord();
        setFieldLengthValueForFullRecord("1800");
    }

    public EditRecordPage setFullEditedData(EditData editData) {
        inputFullRecord(editData);
        return new EditRecordPage();
    }

    public EditRecordPage setSingleData(EditData editData) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getFieldNameForEdit());
        actions.click();
        actions.sendKeys(editData.getFieldNameEdit());
        actions.moveToElement(getColumnNameForEdit());
        actions.click();
        actions.sendKeys(editData.getFieldColumnNameEdit());
        actions.moveToElement(getLength());
        actions.click();
        actions.sendKeys(editData.getFieldLengthValueEdit());
        actions.build().perform();
        return new EditRecordPage();
    }

    public void InputEntityData(CreateEntityData createEntityData) {
        clickEntityNameEdit();
        clearEntityNameEdit();
        setEntityNameEdit(createEntityData.getEntityName());
        clickEntitySchemaNameEdit();
        clearEntitySchemaNameEdit();
        setSchemaNameEdit(createEntityData.getEntitySchemaName());
        clickEntityTableNameEdit();
        clearEntityTableNameEdit();
        setEntityTableNameEdit(createEntityData.getEntityTableName());
    }

    public EditRecordPage setIntoEntityEditedData(CreateEntityData createEntityData) {
        InputEntityData(createEntityData);
        return new EditRecordPage();
    }
}
