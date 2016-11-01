package UISuiteTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by Salome on 31.08.2016.
 */

public class CreateRecordPageValidation extends CreateEntityRecordPage {

    @FindBy(css = "div[class = 'message errorM3']")
    private WebElement validatorMessage;

    @FindBy(css = "input[type='submit'][value='Save']")
    private WebElement saveButton;

    @FindBy(css = "input[type= 'button'][value='Create']")
    private WebElement inputFieldRecord;

    public CreateRecordPageValidation() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSaveButton() {
        return this.saveButton;
    }

    public WebElement getInputFieldRecord() {
        return this.inputFieldRecord;
    }

    public WebElement getValidationMessage() {
        return this.validatorMessage;
    }

    public String getValidationMessageText() {
        return getValidationMessage().getText();
    }

    public CreateRecordPageValidation saveInvalidRecordButton() {
        getSaveButton().click();
        return new CreateRecordPageValidation();
    }

    public CreateFieldPage inputInvalidField() {
        getInputFieldRecord().click();
        return new CreateFieldPage();
    }
}
