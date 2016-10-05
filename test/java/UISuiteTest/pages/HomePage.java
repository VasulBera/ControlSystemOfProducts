package UISuiteTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static UISuiteTest.ApplicationUtil.Application.driver;
import static UISuiteTest.ConstantUtils.constatntValues.*;

/**
 * Created by sriznych on 25.08.2016.
 */

public class HomePage {

    @FindBy(xpath = "//div[contains(@class, 'title') and text()='Entity List')]")
    private WebElement tableTitle;

    @FindBy(xpath = "//input[contains(@id,'CreateEntityButton')]")
    private WebElement createEntityButton;

    @FindBy(xpath = "//a[contains(@id, 'entity-name-link') and text()='" + ENTITY_NAME + "']")
    private WebElement newEntityName;

    @FindBy(xpath = "//td[contains(@id, 'entity-schema-name') and text()='" + ENTITY_SCHEMA_NAME + "']")
    private WebElement newSchemaName;

    @FindBy(xpath = "//td[contains(@id, 'entityTable') and text()='" + ENTITY_TABLE_NAME + "']")
    private WebElement newTableName;

    @FindBy(xpath = "//input[contains(@id, 'fieldNameCreated')]")
    private WebElement fieldName;

    @FindBy(xpath = "//input[contains(@id, 'fieldColumnNameCreated')]")
    private WebElement fieldColumnName;

    @FindBy(xpath = "//input[contains(@id, 'field-length')]")
    private WebElement fieldLengthValue;

    @FindBy(xpath = "//a[contains(@id,'entity-name-link')]")
    private List<WebElement> entityNameList;

    @FindBy(xpath = "//td[contains(@id, 'entity-schema-name')]")
    private List<WebElement> schemaNameList;

    @FindBy(xpath = "//td[contains(@id, 'entity-schema-name')]/following-sibling::td[contains(@id, 'entityTable') and contains(@class, 'dataCell')]")
    private List<WebElement> tableNameList;

    @FindBy(xpath = "//a[contains(@id,'entity-name-link')]")
    private List<WebElement> deleteLinkList;



    //@FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'EntityName']")
    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'EntityNameForEdit']")
    private WebElement recordForSingleEdit;

    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'FullEntityNameForEdit']")
    private WebElement recordForFullEdit;

    @FindBy(xpath = "//a[contains(@onclick, '" + idForEntityTable + "') and text() = 'Delete']")
    private WebElement deleteEntityRecord;

    @FindBy(xpath = "//a[contains(@onclick, '" + idForFullRecord + "') and text() = 'Delete']")
    private WebElement deleteFULLRecord;



    @FindBy(xpath = "//a[contains(@name, 'entityTable')]")
    private List<WebElement> testDeleteRecordLink;

    @FindBy(xpath = "//tr[contains(@class, 'dataRow')]")
    private List<WebElement> tableRowList;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getEditFullRecord(){
        return this.recordForFullEdit;
    }

    public EditRecordPage goToEditFullRecord(){
        getEditFullRecord().click();
        return new EditRecordPage();
    }


    public WebElement getFullRecord(){
        return this.deleteFULLRecord;
    }

    public List<WebElement> getTableRowList() {
        return this.tableRowList;
    }

    public WebElement getDeleteFULLRecord(){
        return this.deleteFULLRecord;
    }

    public ConfirmDesicionPage deleteFullRecord(){
        getDeleteFULLRecord();
        return new ConfirmDesicionPage();
    }

    public WebElement getDeleteEntityRecord() {
        return this.deleteEntityRecord;
    }

    public ConfirmDesicionPage deleteRecord() {
        getDeleteEntityRecord().click();
        return new ConfirmDesicionPage();
    }

    public WebElement getRecordForSingleEdit() {
        return this.recordForSingleEdit;
    }

    public EditRecordPage gotoEditEntityRecordPage() {
        getRecordForSingleEdit().click();
        return new EditRecordPage();
    }

    public EditRecordPage gotoEditFullRecordPage() {
        getFullRecord().click();
        return new EditRecordPage();
    }

    public List<WebElement> getTableNameList() {
        return this.tableNameList;
    }

    public List<WebElement> getSchemaNameList() {
        return this.schemaNameList;
    }

    public List<WebElement> getEntityNameList() {
        return this.entityNameList;
    }

    public List<WebElement> getDeleteListLink() {
        return this.testDeleteRecordLink;
    }

    public WebElement getTableTitle() {
        return this.tableTitle;
    }

    public WebElement getCreateEntityButton() {
        return this.createEntityButton;
    }

    public String getTitleText() {
        return getTableTitle().getText();
    }

    public CreateEntityRecordPage createRecordButton() {
        getCreateEntityButton().click();
        return new CreateEntityRecordPage();
    }

    public ConfirmDesicionPage CheckDeleteLink(List<WebElement> rows) throws InterruptedException {
        for (WebElement elementRows : rows) {
            List<WebElement> innerList = elementRows.findElements(By.xpath("//td"));
            innerList.get(0).findElement(By.xpath("//span")).findElement(By.xpath("//a")).click();

        }
        return new ConfirmDesicionPage();
    }
}
// if (element.findElement(By.xpath("//tr[contains(@class, 'dataRow')]//following-sibling::td[contains(@id, 'entityTable') and text() = 'ww']")).isDisplayed())