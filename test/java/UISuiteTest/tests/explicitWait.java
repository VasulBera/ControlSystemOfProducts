package UISuiteTest.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 21.09.2016.
 */
public class explicitWait {

    @FindBy(id = "username")
    private WebElement logInBox;

    @FindBy(id = "password")
    private WebElement passwordBox;

    @FindBy(id = "Login")
    private  WebElement loginBtn;

    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'FullEntityNameForEdit']")
    private WebElement goToEditPage;

    @FindBy(xpath = "//span[contains(@id, fieldTable) and text() = 'FullFieldNameForEdit']")
    private WebElement blockField;

    public explicitWait() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public WebElement getLogInBox(){
        return this.logInBox;
    }

    public WebElement getPasswordBox(){
        return this.passwordBox;
    }

    public WebElement getLoginBtn(){
        return this.loginBtn;
    }

    public void setLoginValue(String loginValue) {getLogInBox().sendKeys(loginValue);}

    public void setPasswordValue(String passwordValue) {
        getPasswordBox().sendKeys(passwordValue);
    }

    public void inputData(){
        getLogInBox().click();
        getLogInBox().clear();
        setLoginValue("sriznuchok@gmail.com");

        getPasswordBox().click();
        getPasswordBox().clear();
        setPasswordValue("1a4b9c16dd");
    }
    @Test
    public void explicitWait(){

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://c.eu6.visual.force.com/apex/Entity_List");


       /* WebElement loginBox = driver.findElement(By.id("username"));
        loginBox.sendKeys("sriznuchok@gmail.com");

        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys("1a4b9c16dd");

        WebElement buttonLogIn = driver.findElement(By.id("Login"));
        buttonLogIn.click();

        WebElement goToEditPage = driver.findElement(By.xpath("//a[contains(@id, entity-name-link) and text() = 'FullEntityNameForEdit']"));
        goToEditPage.click();

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@id, fieldTable) and text() = 'FullFieldNameForEdit']")));
        driver.findElement(By.xpath("//span[contains(@id, fieldTable) and text() = 'FullFieldNameForEdit']")).click();
        driver.findElement(By.xpath("//span[contains(@id, fieldTable) and text() = 'FullFieldNameForEdit']")).clear();*/
    }
}
