package UISuiteTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static UISuiteTest.ApplicationUtil.Application.driver;

/**
 * Created by sriznych on 31.08.2016.
 */
public class HelpPage {

    @FindBy(css = "li[class = 'wt-Entity-List']")
    private WebElement entityList;

    HelpPage()  {
        PageFactory.initElements(driver, this);
    }

    public WebElement getEntityList() {
        return this.entityList;
    }

    public HomePage gotoHomePage() {
        getEntityList().click();
        return new HomePage();
    }
}
