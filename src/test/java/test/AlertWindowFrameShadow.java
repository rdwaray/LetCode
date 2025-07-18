package test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.*;

import java.io.PrintWriter;

public class AlertWindowFrameShadow {
    private WebDriver driver = driverFactory.getDriver();
    private PrintWriter log = driverFactory.getLog();

    @Given("I go to Alert test page")
    public void iGoToAlertTestPage() {
        By alertTest = By.xpath("//a[normalize-space()='Dialog']");
        UtilityElement.safeClick(driver,alertTest,log);
    }

    @Then("I interact with Accept the Alert")
    public void iInteractWithAcceptTheAlert() {
        By alert = By.id("accept");
        UtilityElement.safeClick(driver,alert,log);
        UtilityAlert.acceptAlert(driver,log);
    }

    @And("I interact with Dismiss the Alert & print the alert text")
    public void iInteractWithDismissTheAlertPrintTheAlertText() {
        By alert = By.id("confirm");
        UtilityElement.safeClick(driver,alert,log);
        UtilityAlert.getAlertText(driver,log);
        UtilityAlert.dismissAlert(driver,log);
    }

    @And("I interact with Type your name & accept")
    public void iInteractWithTypeYourNameAccept() {
        By alert = By.id("prompt");
        UtilityElement.safeClick(driver,alert,log);
        UtilityAlert.sendKeysToAlert(driver,"Ray",log);
    }

    @And("I interact with Sweet alert \\(Modal)")
    public void iInteractWithSweetAlertModal() {
        By alert = By.id("modern");
        UtilityElement.safeClick(driver,alert,log);
        UtilityElement.pause(1000,log);
        By modal = By.cssSelector("button[aria-label='close']");
        By modalText = By.cssSelector("p[class='title']");
        UtilityValidation.getElementText(driver,modalText,log);
        UtilityElement.safeClick(driver,modal,log);
    }

    @Given("I go to Window test page")
    public void iGoToWindowTestPage() {
        By windowTest = By.xpath("//a[normalize-space()='Tabs']");
        UtilityElement.safeClick(driver,windowTest,log);
    }

    @Then("I click on Goto Home")
    public void iClickOnGotoHome() {
        By buttonHome = By.id("home");
        UtilityElement.safeClick(driver,buttonHome,log);
    }

    @And("I go back")
    public void iGoBack() {
        driver.navigate().back();
    }

    @When("I click on Open muiltple windows")
    public void iClickOnOpenMuiltpleWindows() {
        By mutltipleWindows = By.id("multi");
        UtilityElement.safeClick(driver,mutltipleWindows,log);
    }

    @Then("I Print the title of the page")
    public void iPrintTheTitleOfThePage() {
        driver.getTitle();
    }

    @And("I go back to main page")
    public void iGoBackToMainPage() {
        UtilityWindow.switchToDefaultTab(driver,log);
    }

    @And("I close child page")
    public void iCloseChildPage() {
        UtilityWindow.printAllWindowTitles(driver,log);
        UtilityWindow.switchToWindowByIndex(driver,1,log);
        UtilityWindow.closeCurrentTab(driver,log);
        UtilityWindow.switchToWindowByIndex(driver,0,log);

    }

    @Given("I go to Frame test page")
    public void iGoToFrameTestPage() {
        By frameTest = By.xpath("//a[normalize-space()='Inner HTML']");
        UtilityElement.safeClick(driver,frameTest,log);
    }

    @When("I switch to parent frame")
    public void iSwitchToParentFrame() {
        By parentFrame = By.id("firstFr");
        UtilityFrame.switchToFrame(driver,parentFrame,log);

    }

    @Then("I fill first name")
    public void iFillFirstName() {
        By form1 = By.xpath("/html/body/app-root/app-frame-content/div/div/form/div[1]/div/input");
        UtilityElement.sendKeys(driver,form1,"Ray",log);
    }

    @And("I fill last name")
    public void iFillLastName() {
        By form2 = By.xpath("/html/body/app-root/app-frame-content/div/div/form/div[2]/div/input");
        UtilityElement.sendKeys(driver,form2,"Dimas",log);
    }

    @Then("I switch to iframe")
    public void iSwitchToIframe() {
        By frameLocator = By.xpath("//iframe[@src='innerframe']");
        driver.switchTo().frame(driver.findElement(frameLocator));
    }

    @And("I fill email")
    public void iFillEmail() {
        By formEmail = By.xpath("/html/body/app-root/app-innerframe/div/div/div/div/div/input");
        UtilityElement.sendKeys(driver,formEmail,"Rat@mf.com",log);
    }

    @Given("I go to Shadow DOM test page")
    public void iGoToShadowDOMTestPage() {
        By shadowTest = By.xpath("//a[normalize-space()='DOM']");
        UtilityElement.findElement(driver,shadowTest,log);
        UtilityElement.safeClick(driver,shadowTest,log);
    }

    @Then("I interact with first element")
    public void iInteractWithFirstElement() {
        By shadowHost = By.cssSelector("div.control");
        By insideShadow = By.cssSelector("input#fname");

        WebElement inputField = UtilityShadow.findElementInOpenShadowRoot(driver, shadowHost, insideShadow, log);
        inputField.sendKeys("Halo");

    }

    @Then("I interact with second element")
    public void iInteractWithSecondElement() {
        By closed = By.id("lname");
        UtilityValidation.printElementDetails(driver,closed,log);

    }

    @Then("I interact with third element")
    public void iInteractWithThirdElement() {
        By closed = By.id("email");
        UtilityValidation.printElementDetails(driver,closed,log);
    }
}
