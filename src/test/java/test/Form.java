package test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.UtilityDropdown;
import utils.UtilityElement;
import utils.UtilityNavigation;
import utils.driverFactory;


import java.io.*;
import java.util.Arrays;
import java.util.List;

import utils.driverFactory;
import static utils.driverFactory.getLog;

public class Form {
    private WebDriver driver = driverFactory.getDriver();
    private PrintWriter log = driverFactory.getLog();
    private PrintWriter writeBothLog = driverFactory.getLog();

    @Given("I go to Form test page")
    public void iGoToFormTestPage() {
        By  formInput = By.xpath("//a[normalize-space()='All in One']");
        UtilityElement.findElement(driver,formInput,log);
        UtilityElement.safeClick(driver,formInput,log);
    }

    @Then("I fill form")
    public void iFillForm() {
        fillCSVV2(driver, driverFactory.getLog(), "src/test/resources/data/input/sample_data.csv");    }

    public static void fillCSVV2(WebDriver driver, PrintWriter log, String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = reader.readAll();
            records.remove(0); // Skip header

            for (String[] row : records) {
                if (row.length < 13) {
                    driverFactory.writeBothLog("[INVALID LENGTH] " + Arrays.toString(row));
                    continue;
                }
                driverFactory.writeBothLog("[INFO] Start filling form for: " + Arrays.toString(row));
                    String firstName    = row[0];
                    String lastName     = row[1];
                    String emaillist    = row[2];
                    String countryCode  = row[3];
                    String phoneNumber  = row[4];
                    String address1     = row[5];
                    String address2     = row[6];
                    String state        = row[7];
                    String postalCode   = row[8];
                    String country      = row[9];
                    String dob          = row[10];
                    String gender       = row[11];
                    String agree        = row[12];

                    By fisrtNameBox = By.id("firstname");
                    UtilityElement.sendKeys(driver,fisrtNameBox,firstName,log);

                    By lastNameBox = By.id("lasttname");
                    UtilityElement.sendKeys(driver,lastNameBox,lastName,log);

                    By emailBox = By.id("email");
                    UtilityElement.sendKeys(driver,emailBox,emaillist,log);

                    By countryCodeBox = By.xpath("/html/body/app-root/app-forms/section/div/div/div[1]/div/div/form/div[2]/div[2]/div/div/div/select");
                    UtilityDropdown.selectDropDownByText(driver,countryCodeBox,countryCode,log);

                    By phoneNumberBox = By.id("Phno");
                    UtilityElement.sendKeys(driver,phoneNumberBox,phoneNumber,log);

                    By adress1Box = By.id("Addl1");
                    UtilityElement.sendKeys(driver,adress1Box,address1,log);

                    By adress2Box = By.id("Addl2");
                    UtilityElement.sendKeys(driver,adress2Box,address2,log);

                    By stateBox = By.id("state");
                    UtilityElement.sendKeys(driver,stateBox,state,log);

                    By postalBox = By.id("postalcode");
                    UtilityElement.sendKeys(driver,postalBox,postalCode,log);

                    By dobBox = By.id("Date");
                    UtilityElement.sendKeys(driver,dobBox,dob,log);

                    By countryBox = By.xpath("/html/body/app-root/app-forms/section/div/div/div[1]/div/div/form/div[5]/div[2]/div/div/div/select");
                    UtilityDropdown.selectDropDownByText(driver,countryBox,country,log);

                    By genderLocator;
                    switch (gender.toLowerCase()) {
                        case "male":
                            genderLocator = By.xpath("//input[@id='male']");
                            break;
                        case "female":
                            genderLocator = By.xpath("//input[@id='female']");
                            break;
                        case "other":
                            genderLocator = By.xpath("//input[@id='trans']");
                            break;
                        default:
                            log.println("[INVALID GENDER] " + Arrays.toString(row));
                            log.flush();
                            driver.navigate().refresh();
                            UtilityElement.pause(1000,log);
                            continue;
                    }
                    UtilityElement.safeClick(driver,genderLocator,log);

                    boolean agreeCheck = Boolean.parseBoolean(agree);
                    WebElement agreebox = driver.findElement(By.xpath("//input[@type='checkbox']"));

                    if (agreeCheck && !agreebox.isSelected()) {
                        UtilityElement.safeClick(driver,agreebox,log);
                    } else if (!agreeCheck && agreebox.isSelected()) {
                        UtilityElement.safeClick(driver,agreebox,log);
                    }
                    UtilityElement.pause(500,log);

                    By submitQuery = By.xpath("//input[@type='submit']");
                    UtilityElement.findElement(driver,submitQuery,log);
                    UtilityElement.safeClick(driver,submitQuery,log);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        }

