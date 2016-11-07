package UISuiteTest.ApplicationUtil;

import UISuiteTest.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by sriznych on 25.08.2016.
 */

public class Application {

    private ApplicationSources applicationSources;
    public static WebDriver driver;

    public Application(ApplicationSources applicationSources) {
        this.applicationSources = applicationSources;
        System.out.println("\t*** Application Sources");
        System.out.println("\t" + applicationSources.getLoginUrl());
        System.out.println("\t" + applicationSources.getLogoutUrl());
        System.out.println("\t" + applicationSources.getBrowserName());
    }

    private void initBrowser() {
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().timeouts()
                    .implicitlyWait(applicationSources.getImplicitTimeOut(), TimeUnit.SECONDS);
        }
    }

    private void initChromeBrowser() {
        System.setProperty("webdriver.chrome.driver", "D://11//chromedriver.exe");
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().timeouts()
                    .implicitlyWait(applicationSources.getImplicitTimeOut(), TimeUnit.SECONDS);
        }
    }

    public LoginPage load() {
        initBrowser();
        driver.get(applicationSources.getLoginUrl());
        return new LoginPage();
    }

    public LoginPage loadChrome() {
        initChromeBrowser();
        driver.get(applicationSources.getLoginUrl());
        return new LoginPage();
    }

    public LoginPage logout() {
        if (driver == null) {
            return load();
        }
        driver.get(applicationSources.getLogoutUrl());
        return new LoginPage();
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void close() {
        if (driver != null) {
            driver.close();
        }
    }
}
