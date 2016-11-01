package UISuiteTest.pages;

import UISuiteTest.LogInData.Account;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 25.08.2016.
 */

public class LoginPage {

    @FindBy(id = "username")
    private WebElement usernameValue;

    @FindBy(id = "password")
    private WebElement passwordValue;

    @FindBy(id = "Login")
    private WebElement logInButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameValue() {
        return this.usernameValue;
    }

    public WebElement getPasswordValue() {
        return this.passwordValue;
    }

    public WebElement getLogInButton() {
        return this.logInButton;
    }

    public void setUsernameValue(String usernameLogin) {
        getUsernameValue().sendKeys(usernameLogin);
    }

    public void setPasswordValue(String passwordValue) {
        getPasswordValue().sendKeys(passwordValue);
    }

    public void clearUsernameValue() {
        getUsernameValue().clear();
    }

    public void clearPasswordValue() {
        getPasswordValue().clear();
    }

    public void clickUsernameValue() {
        getUsernameValue().click();
    }

    public void clickPasswordValue() {
        getPasswordValue().click();
    }

    public void clickLogInButton() {
        getLogInButton().click();
    }

    public void setLoginData(Account account) {
        clickUsernameValue();
        clearUsernameValue();
        setUsernameValue(account.getUserName());
        clickPasswordValue();
        clearPasswordValue();
        setPasswordValue(account.getPassword());
        clickLogInButton();
    }

    public HelpPage successLogin(Account account) {
        setLoginData(account);
        return new HelpPage();
    }
}
