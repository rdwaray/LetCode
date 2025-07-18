package test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setting.baseStep;
import utils.*;

import java.io.PrintWriter;

public class InputHandling {
    private WebDriver driver = driverFactory.getDriver();
    private PrintWriter log = driverFactory.getLog();

    @Given("I go to Input test page")
    public void iGoToInputTestPage() {
        By inputTest = By.xpath("//a[normalize-space()='Edit']");
        UtilityElement.safeClick(driver,inputTest,log);
    }

    @And("I fill full name with {string}")
    public void iFillFullNameWith(String name) {
        By nameBox = By.id("fullName");
        UtilityElement.sendKeys(driver,nameBox,name,log);
    }

    @Then("I click tab on keyboard")
    public void iClickTabOnKeyboard() {
        By nameBox = By.id("fullName");
        UtilityElement.pressKeyButton(driver,nameBox, Keys.TAB,log);
    }

    @And("I extract text on text box")
    public void iExtractTextOnTextBox() {
        By getTextBox = By.id("getMe");
        UtilityValidation.getElementText(driver,getTextBox,log);
    }

    @And("I clear text box")
    public void iClearTextBox() {
        WebElement clearTextBox = driver.findElement(By.id("clearMe"));
        clearTextBox.clear();
    }

    @And("I confirm edit field is disabled")
    public void iConfirmEditFieldIsDisabled() {
        By noEdit = By.id("noEdit");
        UtilityValidation.printElementDetails(driver,noEdit,log);
    }

    @And("I confirm field is readonly")
    public void iConfirmFieldIsReadonly() {
        By readOnly = By.id("dontwrite");
        UtilityValidation.printElementDetails(driver,readOnly,log);
    }

    @Given("I go to Button test page")
    public void iGoToButtonTestPage() {
        By buttonTest = By.xpath("//a[normalize-space()='Click']");
        UtilityElement.safeClick(driver,buttonTest,log);
    }

    @And("I check Goto Home button details")
    public void iCheckGotoHomeButtonDetails() {
        By homeButton = By.id("home");
        UtilityElement.safeClick(driver,homeButton,log);
        UtilityNavigation.backPage(driver,log);
    }

    @And("I check Find Location button details")
    public void iCheckFindLocationButtonDetails() {
        By findLocation = By.id("position");
        UtilityValidation.printElementDetails(driver,findLocation,log);
    }

    @And("I check What is my color button details")
    public void iCheckWhatIsMyColorButtonDetails() {
        By color = By.id("color");
        UtilityValidation.printElementDetails(driver,color,log);
    }

    @And("I check How tall and fat am i button details")
    public void iCheckHowTallAndFatAmIButtonDetails() {
        By sizeButton = By.id("property");
        UtilityValidation.printElementDetails(driver,sizeButton,log);
    }

    @And("I check disabled button details")
    public void iCheckDisabledButtonDetails() {
        By disabledButton = By.id("isDisabled");
        UtilityValidation.printElementDetails(driver,disabledButton,log);
    }

    @And("I check Hold button details")
    public void iCheckHoldButtonDetails() {
        By holdButton = By.xpath("//div[6]//div[1]//button[1]");
        UtilityElement.longPress(driver,holdButton,3000,log);
        UtilityElement.pause(1000,log);
        UtilityValidation.printElementDetails(driver,holdButton,log);
    }

    @Given("I go to Dropdown test page")
    public void iGoToDropdownTestPage() {
        By selectTest = By.xpath("//a[normalize-space()='Drop-Down']");
        UtilityElement.safeClick(driver, selectTest,log);
    }

    @And("I choose  values dropdown one with visible text")
    public void iChooseValuesDropdownOneWithVisibleText() {
        By dropdown1 = By.xpath("//*[@id=\"fruits\"]");
        UtilityDropdown.selectDropDownByText(driver,dropdown1,"Apple",log);
    }

    @Then("I choose multiple values dropdown two")
    public void iChooseMultipleValuesDropdownTwo() {

        By dropdown2 = By.xpath("//*[@id=\"superheros\"]");
        UtilityDropdown.selectDropDownByText(driver,dropdown2,"Ant-Man",log);
        UtilityDropdown.selectDropDownByText(driver,dropdown2,"Aquaman",log);
        UtilityDropdown.selectDropDownByText(driver,dropdown2,"Captain America",log);
        UtilityDropdown.selectDropDownByText(driver,dropdown2,"Elektra",log);
    }

    @When("I choose last values of dropwdown three")
    public void iChooseLastValuesOfDropwdownThree() {
        By dropdown3 = By.xpath("//*[@id=\"lang\"]");
        UtilityDropdown.selectDropDownByText(driver,dropdown3,"C#",log);
    }

    @Then("I print all the option")
    public void iPrintAllTheOption() {
        By dropdown3 = By.xpath("//*[@id=\"lang\"]");
        UtilityDropdown.printDropDownOptions(driver,dropdown3,log);
    }

    @And("I choose values dropdown four by index")
    public void iChooseValuesDropdownFourByIndex() {
        By dropdown4 = By.xpath("//*[@id=\"country\"]");
        UtilityDropdown.selectDropDownByIndex(driver,dropdown4,11,log);
    }

    @Given("I go to Radio test page")
    public void iGoToRadioTestPage() {
        By radioTest = By.xpath("//a[normalize-space()='Toggle']");
        UtilityElement.safeClick(driver,radioTest,log);
    }

    @Then("I check Select any one")
    public void iCheckSelectAnyOne() {
        By button1 = By.id("yes");
        By button2 = By.id("no");
        UtilityElement.safeClick(driver,button2,log);
        UtilityElement.safeClick(driver,button1,log);


    }

    @And("I check Cofirm you can select only one radio button")
    public void iCheckCofirmYouCanSelectOnlyOneRadioButton() {
        By button1 = By.id("one");
        By button2 = By.id("two");
        UtilityValidation.printElementDetails(driver,button1,log);
        UtilityValidation.printElementDetails(driver,button2,log);
        UtilityElement.safeClick(driver,button2,log);
        UtilityElement.safeClick(driver,button1,log);
    }

    @And("I check Find the bug")
    public void iCheckFindTheBug() {
        By button1 = By.id("nobug");
        By button2 = By.id("bug");
        UtilityValidation.printElementDetails(driver,button1,log);
        UtilityValidation.printElementDetails(driver,button2,log);
        UtilityElement.safeClick(driver,button2,log);
        UtilityElement.safeClick(driver,button1,log);
    }

    @And("I check Find which one is selected")
    public void iCheckFindWhichOneIsSelected() {
        By button1 = By.xpath("//input[@id='foo']");
        By button2 = By.xpath("//input[@id='notfoo']");
        UtilityValidation.printElementDetails(driver,button1,log);
        UtilityValidation.printElementDetails(driver,button2,log);
    }

    @And("I check Confirm last field is disabled")
    public void iCheckConfirmLastFieldIsDisabled() {
        By button1 = By.xpath("//input[@id='going']");
        By button2 = By.xpath("//input[@id='notG']");
        By button3 = By.xpath("//input[@id='maybe']");
        UtilityValidation.printElementDetails(driver,button1,log);
        UtilityValidation.printElementDetails(driver,button2,log);
        UtilityValidation.printElementDetails(driver,button3,log);

    }

    @And("I check Find if the checkbox is selected?")
    public void iCheckFindIfTheCheckboxIsSelected() {
        By button1 = By.xpath("//label[normalize-space()='Remember me']//input[@type='checkbox']");
        UtilityValidation.printElementDetails(driver,button1,log);
    }

    @And("I check Accept the T&C")
    public void iCheckAcceptTheTC() {
        By button1 = By.xpath("//div[7]//label[2]//input[1]");
        UtilityElement.safeClick(driver,button1,log);
        UtilityValidation.printElementDetails(driver,button1,log);
    }

    @Given("I go to Calendar test page")
    public void iGoToCalendarTestPage() {
        By calendarTest = By.xpath("//a[normalize-space()='Date & Time']");
        UtilityElement.findElement(driver,calendarTest,log);
        UtilityElement.safeClick(driver,calendarTest,log);
    }

    @Then("I fill birthday date")
    public void iFillBirthdayDate() {
        By inputDate = By.id("birthday");
        UtilityElement.sendKeys(driver,inputDate,"02121999",log);
        UtilityElement.pause(1000,log);
        By result = By.cssSelector(".label.pt-3");
        UtilityValidation.getElementText(driver,result,log);
    }
}
