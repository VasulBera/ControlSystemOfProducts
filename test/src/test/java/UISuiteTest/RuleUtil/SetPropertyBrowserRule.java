package UISuiteTest.RuleUtil;

import com.jayway.restassured.RestAssured;
import org.junit.rules.ExternalResource;

/**
 * Created by sriznych on 01.09.2016.
 */
public class SetPropertyBrowserRule extends ExternalResource {

    @Override
    public void before() {

        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
    }

    @Override
    public void after() {
        System.out.println(" After SetPropertyBrowserRule rule class!!!");
    }
}
