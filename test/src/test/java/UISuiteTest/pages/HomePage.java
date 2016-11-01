package UISuiteTest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static UISuiteTest.ApplicationUtil.Application.driver;
import static UISuiteTest.ConstantUtils.ConstantValues.idForEntityTable;

/**
 * Created by sriznych on 25.08.2016.
 */

public class HomePage {

    @FindBy(xpath = "//a[contains(@onclick, 'FULLENTITYSCHEMANAMEFOREDITFULLTABLENAMEFOREDIT') and text() = 'Delete']")
    private WebElement deleteFullEditedRecord;

    @FindBy(xpath = "//a[contains(@onclick, 'ENTITYSCHEMANAMEFOREDITENTITYTABLENAMEFOREDIT') and text() = 'Delete']")
    private WebElement deleteEditedEntityRecord;

    @FindBy(xpath = "//div[contains(@class, 'title') and text()='Entity List']")
    private WebElement tableTitle;

    @FindBy(css = "div[id*='entityTable'][class='bPageBlock brandSecondaryBrd apexDefaultPageBlock secondaryPalette']")
    private WebElement tableEntityList;

    @FindBy(xpath = "//input[contains(@id,'CreateEntityButton')]")
    private WebElement createEntityButton;

    @FindBy(xpath = "//a[contains(@id,'entity-name-link')]")
    private List<WebElement> entityNameList;

    @FindBy(xpath = "//td[contains(@id, 'entity-schema-name')]")
    private List<WebElement> schemaNameList;

    @FindBy(xpath = "//td[contains(@id, 'entity-schema-name')]/following-sibling::td[contains(@id, 'entityTable') and contains(@class, 'dataCell')]")
    private List<WebElement> tableNameList;

    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'EntityNameForEdit']")
    private WebElement editEntityTableRecord;

    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'EntityNameForIncorrectEdit']")
    private WebElement editEntityRecordWithIncorrectData;

    @FindBy(xpath = "//a[contains(@id, entity-name-link) and text() = 'FullEntityNameForEdit']")
    private WebElement editFullRecord;

    @FindBy(xpath = "//a[contains(@onclick, '" + idForEntityTable + "') and text() = 'Delete']")
    private WebElement deleteEntityRecord;

    @FindBy(xpath = "//a[contains(@onclick, 'FULLRECORDSCHEMAFULLRECORDTABLE') and text() = 'Delete']")
    private WebElement deleteFullRecord;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getEditEntityRecordWithIncorrectData() {return this.editEntityRecordWithIncorrectData; }

    public WebElement getDeleteEntityRecord() {
        return this.deleteEntityRecord;
    }

    public WebElement getEditEntityTableRecord() {
        return this.editEntityTableRecord;
    }

    public WebElement getDeleteFullEditedRecord() {return  this.deleteFullEditedRecord; }

    public WebElement getDeleteEditedEntityRecord() {return  this.deleteEditedEntityRecord; }

    public WebElement getTableEntityList() {return this.tableEntityList; }

    public WebElement getDeleteFullRecord(){
        return this.deleteFullRecord;
    }

    public WebElement getTableTitle() {
        return this.tableTitle;
    }

    public WebElement getCreateEntityButton() {
        return this.createEntityButton;
    }

    public WebElement getEditFullRecord(){
        return this.editFullRecord;
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

    public String getTableTitleText() {
        return getTableTitle().getText();
    }

    public void clickDeleteFullRecord(){
        getDeleteFullRecord().click();
    }

    public ConfirmDesicionPage deleteFullRecordLink(){
        clickDeleteFullRecord();
        return new ConfirmDesicionPage();
    }

    public ConfirmDesicionPage deleteRecord() {
        getDeleteEntityRecord().click();
        return new ConfirmDesicionPage();
    }

    public EditRecordPage gotoEditEntityRecordPage() {
        getEditEntityTableRecord().click();
        return new EditRecordPage();
    }

    public ConfirmDesicionPage deleteEditedFullRecord(){
        getDeleteFullEditedRecord().click();
        return new ConfirmDesicionPage();
    }

    public ConfirmDesicionPage deleteEditedEntityRecord(){
        getDeleteEditedEntityRecord().click();
        return new ConfirmDesicionPage();
    }

    public EditRecordPage goToEditFullRecord(){
        getEditFullRecord().click();
        return new EditRecordPage();
    }

    public EditRecordPage goToEditRecordWithIncorrectData() {
        getEditEntityRecordWithIncorrectData().click();
        return new EditRecordPage();
    }

    public CreateEntityRecordPage createRecordButton() {
        getCreateEntityButton().click();
        return new CreateEntityRecordPage();
    }
}
