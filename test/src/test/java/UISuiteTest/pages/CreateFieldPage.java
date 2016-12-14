package UISuiteTest.pages;

import UISuiteTest.LogInData.CreateFieldData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 02.09.2016.
 */

public class CreateFieldPage extends CreateEntityRecordPage {

    @FindBy(xpath = "//input[contains(@id, 'fieldNameCreated')]")
    private WebElement fieldName;

    @FindBy(xpath = "//input[contains(@id, 'fieldColumnNameCreated')]")
    private WebElement fieldColumnName;

    @FindBy(xpath = "//input[contains(@id, 'field-length')]")
    private WebElement fieldLengthValue;

    public CreateFieldPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getFieldName() {
        return this.fieldName;
    }

    public WebElement getFieldColumnName() {
        return this.fieldColumnName;
    }

    public WebElement getFieldLength() {
        return this.fieldLengthValue;
    }

    public void clickFieldName() {
        getFieldName().click();
    }

    public void clickFieldColumnName() {
        getFieldColumnName().click();
    }

    public void clickFieldLength() {
        getFieldLength().click();
    }

    public void setFieldName(String fieldName) {
        getFieldName().sendKeys(fieldName);
    }

    public void setFieldColumnName(String fieldColumnName) {
        getFieldColumnName().sendKeys(fieldColumnName);
    }

    public void setFieldLength(String fieldLengthValue) {
        getFieldLength().sendKeys(fieldLengthValue);
    }

    public void clearFieldName() {
        getFieldName().clear();
    }

    public void clearFieldColumnName() {
        getFieldColumnName().clear();
    }

    public void clearFieldLengthValue() {
        getFieldLength().clear();
    }

    public void createFieldRecord(CreateFieldData createFieldData) {
        clickFieldName();
        clearFieldName();
        setFieldName(createFieldData.getFieldName());
        clickFieldColumnName();
        clearFieldColumnName();
        setFieldColumnName(createFieldData.getFieldColumnName());
        clickFieldLength();
        clearFieldLengthValue();
        setFieldLength(createFieldData.getFieldLengthValue());
        clickUniqueCheckBox();
    }

    public CreateFieldPage setFieldData(CreateFieldData createFieldData) {
        createFieldRecord(createFieldData);
        return new CreateFieldPage();
    }

    public CreateRecordPageValidation setInvalidFieldData(CreateFieldData createFieldData)  {
        createFieldRecord(createFieldData);
        return new CreateRecordPageValidation();
    }
}
