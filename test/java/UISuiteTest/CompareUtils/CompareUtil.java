package UISuiteTest.CompareUtils;

import UISuiteTest.pages.ConfirmDesicionPage;
import UISuiteTest.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static UISuiteTest.ConstantUtils.constatntValues.FULL_RECORD_TABLE_NAME;
import static UISuiteTest.ConstantUtils.constatntValues.FULL_RECORD__SCHEMA_NAME;

/**
 * Created by sriznych on 31.08.2016.
 */
public class CompareUtil {


    public static boolean CheckListRecord(List<WebElement> searchResult, String expected) {
        boolean result = false;
        for (WebElement element : searchResult) {
            if (element.getText().equals(expected)) {
                result = true;
            }
        }
        return result;
    }

}
