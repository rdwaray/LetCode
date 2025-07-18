package test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UtilityDropdown;
import utils.UtilityElement;
import utils.UtilityValidation;
import utils.driverFactory;

import java.io.PrintWriter;
import java.util.List;

public class TableDataHandling {
    private WebDriver driver = driverFactory.getDriver();
    private PrintWriter log = driverFactory.getLog();
    @Given("I go to Table test page")
    public void iGoToTableTestPage() {
        By tableTest = By.xpath("//a[normalize-space()='Advance table']");
        UtilityElement.findElement(driver,tableTest,log);
        UtilityElement.safeClick(driver,tableTest,log);

    }

    @When("I search {string}")
    public void iSearch(String uni) {
        UtilityElement.pause(1000,log);
        By searchBox = By.cssSelector("#dt-search-0");
        UtilityElement.sendKeys(driver,searchBox,uni,log);
    }

    @Then("I print the data")
    public void iPrintTheData() {
        By data1 = By.cssSelector("#advancedtable > tbody > tr:nth-child(1)");
        UtilityValidation.getElementText(driver,data1,log);
        By data2 = By.cssSelector("#advancedtable > tbody > tr:nth-child(2)");
        UtilityValidation.getElementText(driver,data2,log);
        By searchBoxs = By.cssSelector("#dt-search-0");
        WebElement searchBox = driver.findElement( By.cssSelector("#dt-search-0"));
        searchBox.clear();
        UtilityElement.pressKeyButton(driver,searchBoxs, Keys.ENTER,log);
    }

    @And("I change entries per page")
    public void iChangeEntriesPerPage() {
        By row = By.cssSelector("#dt-length-0");
        UtilityDropdown.selectDropDownByIndex(driver,row,2,log);
        UtilityDropdown.selectDropDownByIndex(driver,row,0,log);
    }

    @Then("I print all the data")
    public void iPrintAllTheData() {
        for (int i = 0; i < 9; i++) {
            By rowData = By.cssSelector("#advancedtable > tbody");
            UtilityValidation.getElementText(driver, rowData, log);

            UtilityElement.pause(500, log);

            By next = By.cssSelector("button[aria-label='Next']");
            UtilityElement.safeClick(driver, next, log);

            UtilityElement.pause(500, log);
        }

    }

    @Given("I go to Table Simple test page")
    public void iGoToTableSimpleTestPage() {
        By tableTest2 = By.xpath("//a[normalize-space()='Simple table']");
        UtilityElement.findElement(driver,tableTest2,log);
        UtilityElement.safeClick(driver,tableTest2,log);

    }


    @Then("I assert Shopping List")
    public void iAssertShoppingList() {
        WebElement price1 = driver.findElement(By.xpath("//td[normalize-space()='150']"));
        WebElement price2 = driver.findElement(By.xpath("//td[normalize-space()='180']"));
        WebElement price3 = driver.findElement(By.xpath("//td[normalize-space()='48']"));
        WebElement price4 = driver.findElement(By.xpath("//td[normalize-space()='480']"));

        String priceconvert1 = price1.getText().trim();
        String priceconvert2 = price2.getText().trim();
        String priceconvert3 = price3.getText().trim();
        String priceconvert4 = price4.getText().trim();

        int priceakhir1 = Integer.parseInt(priceconvert1);
        int priceakhir2 = Integer.parseInt(priceconvert2);
        int priceakhir3 = Integer.parseInt(priceconvert3);
        int priceakhir4 = Integer.parseInt(priceconvert4);

        int totalInt = priceakhir1 + priceakhir2 + priceakhir3 + priceakhir4;


        WebElement total = driver.findElement(By.xpath("//b[normalize-space()='858']"));
        String totalAkhir = total.getText().trim();
        int totalPrice = Integer.parseInt(totalAkhir);
        UtilityValidation.assertEqualsWithLog(log,totalPrice,totalInt);

    }

    @And("I assert Let's handle it")
    public void iAssertLetSHandleIt() {
//        WebElement table = driver.findElement(By.id("simpletable"));
//        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
//
//        for (WebElement row : rows) {
//            List<WebElement> cells = row.findElements(By.tagName("td"));
//            if (cells.size() >= 4) {
//                String lastName = cells.get(1).getText().trim(); // Kolom kedua = Last Name
//                if (lastName.equals("Raj")) {
//                    WebElement checkbox = cells.get(3).findElement(By.xpath(".//input[@type='checkbox']"));
//                    checkbox.click();
//                    break; // Berhenti setelah menemukan "Raj"
//                }
//            }
//        }
        // Klik radio button di kolom ke-4 jika kolom ke-2 berisi "Raj"
        UtilityValidation.clickElementInTableCell(
                driver,
                By.id("simpletable"),
                1,      // Last name = kolom ke-1
                "Raj",
                3,      // Kolom Present/Absent = kolom ke-3
                By.xpath("//input[@id='second']"),
                log
        );
    }

    @And("I assert Sortable Tables")
    public void iAssertSortableTables() {
        By txt = By.cssSelector("body > app-root > app-table > section > div > div > div.column.is-6-desktop.is-8-tablet > div > div > div > div.card-conetnt > div > table");
        UtilityValidation.getElementText(driver,txt,log);
    }

    @Given("I go to File test page")
    public void iGoToFileTestPage() {
        By fileTest = By.xpath("//a[normalize-space()='File management']");
        UtilityElement.findElement(driver,fileTest,log);
        UtilityElement.safeClick(driver,fileTest,log);

    }

    @Then("I upload file")
    public void iUploadFile() {
//        By fileInput = By.cssSelector("input[type='file']");
//        UtilityElement.sendKeys(driver,fileInput,"resources/data/input/sampleFile.jpeg",log);

    }

    @And("I download file")
    public void iDownloadFile() {
        By download1 = By.xpath("//a[@id='xls']");
        By download2 = By.xpath("// a[@id='pdf']");
        By download3 = By.xpath("//a[@id='txt']");

        UtilityElement.safeClick(driver,download1,log);
        UtilityElement.safeClick(driver,download2,log);
        UtilityElement.safeClick(driver,download3,log);




    }
}
