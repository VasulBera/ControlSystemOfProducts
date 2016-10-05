package UISuiteTest.tests;

import UISuiteTest.LogInData.DataType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static UISuiteTest.ConstantUtils.constatntValues.FULL_RECORD_NAME;

/**
 * Created by sriznych on 01.09.2016.
 */

public class SimpleTest {

    public enum DataType {
        INT, DATE, FLOAT, NVARCHAR, BIT
    }


    @Test
    public void simpleTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://c.eu6.visual.force.com/apex/Entity_List?sfdc.tabName=01r580000009eYd");
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("sriznuchok@gmail.com");


        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("1a4b9c16dd");

        WebElement LogBt = driver.findElement(By.id("Login"));
        LogBt.click();


        WebElement gotoEdit = driver.findElement(By.xpath("//a[contains(@id, entity-name-link) and text() = '" + FULL_RECORD_NAME + "']"));
        gotoEdit.click();

        /*Thread.sleep(4000);
        WebElement createNewEntity = driver.findElement(By.xpath("//input[contains(@id, 'CreateEntityButton')]"));
        createNewEntity.click();

        Thread.sleep(4000);
        WebElement createFieldRType = driver.findElement(By.cssSelector("input[type='button'][value='Create']"));
        createFieldRType.click();


         List<WebElement> listOfLines = driver.findElements(By.xpath("//tr[contains(@class, 'dataRow')]"));
         WebElement lastLine = listOfLines.get(listOfLines.size()-1);
         System.out.println("Text-->" +  lastLine.getText());*/


      /*  Thread.sleep(5000);
        WebElement clickForDelete = driver.findElement(By.xpath("//a[contains(@id, entity-name-link) and text() = '" + FULL_RECORD_NAME + "']"));
        clickForDelete.click();

        WebElement addButon = driver.findElement(By.cssSelector("input[id*= 'fieldTable'][class='btn']"));
        addButon.click();

        Thread.sleep(10000);

     /*   List<WebElement> tmpElement = driver.findElements(By.cssSelector("span[id*='ilecell'][ class= 'inlineEditWrite']"));
        System.out.println("-->" + tmpElement.size());
       WebElement tryType =  tmpElement.get(tmpElement.size() - 1);
        Thread.sleep(5000);
        tryType.click();
        Thread.sleep(5000);*/
//


        //============================================================================================================

        Thread.sleep(4000);
        List<WebElement> listLengthField = driver.findElements(By.xpath("//input[contains(@id , 'field-length')]"));
        WebElement tryLength = listLengthField.get(listLengthField.size() - 1);
        Thread.sleep(4000);
        tryLength.click();

        Thread.sleep(4000);
        Actions actions = new Actions(driver);
        actions.moveToElement(tryLength);
        actions.click();
        actions.sendKeys("7");
        actions.build().perform();

        //=============================================================================================================


       /* Thread.sleep(4000);
        WebElement table_element = driver.findElement(By.xpath("//table[@class='list']"));

        List<WebElement> allRows = table_element.findElements(By.xpath("//tr[contains(@class, 'dataRow')]"));

        System.out.println("count-->" + allRows.size());

// And iterate over them, getting the cells

       // for (WebElement row : allRows) {

            Thread.sleep(3000);
            List<WebElement> cells = table_element.findElements(By.xpath("//td"));
            System.out.println("count-->" + cells.size());

        cells.get(2).click();
      }*/

    }
}
