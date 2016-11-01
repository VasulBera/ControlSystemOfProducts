package UISuiteTest.CompareUtils;

import org.openqa.selenium.WebElement;
import java.util.List;

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
