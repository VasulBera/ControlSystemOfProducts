package UISuiteTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 31.08.2016.
 */

public class ConfirmDesicionPage {

    @FindBy(css = "div[role= 'dialog']")
    private WebElement modalWindow;

    @FindBy(xpath = "//button[contains(@class, 'modal-btn') and text()= 'Delete']")
    private WebElement deleteRecordButton;

    public ConfirmDesicionPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDeleteRecordButton() {
        return this.deleteRecordButton;
    }

    public HomePage confirmDeleteRecord() {
        getDeleteRecordButton().click();
        return new HomePage();
    }
}
