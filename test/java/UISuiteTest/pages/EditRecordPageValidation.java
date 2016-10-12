package UISuiteTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 07.10.2016.
 */

public class EditRecordPageValidation extends EditRecordPage{

    @FindBy(css = "div[class = 'message errorM3']")
    private WebElement validatorMessage;

    @FindBy(css = "input[type='submit'][value='Save']")
    private WebElement saveButton;

    @FindBy(css = "input[type= 'button'][value='Add']")
    private WebElement inputFieldRecord;

    public EditRecordPageValidation() {
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

    public EditRecordPageValidation saveEditedInvalidRecordButton() {
        getSaveButton().click();
        return new EditRecordPageValidation();
    }

    public CreateFieldPage inputEditedInvalidField() {
        getInputFieldRecord().click();
        return new CreateFieldPage();
    }
}
