package UISuiteTest.ApplicationUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by sriznych on 25.08.2016.
 */
public class DriverUtil {


    private DriverUtil() {
    }

 public static WebDriver get(String name) {
        if(name.equalsIgnoreCase("chrome"))
        {
            return new ChromeDriver();
        }
        if(name.equalsIgnoreCase("firefox"))
        {
            return  new FirefoxDriver();
        }
        return null;
    }
}

