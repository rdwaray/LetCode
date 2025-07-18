package test;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.*;

import java.io.PrintWriter;
import java.util.List;

public class POM {

    private WebDriver driver = driverFactory.getDriver();
    private PrintWriter log = driverFactory.getLog();

    @Given("I am on the letcode homepage")
    public void iAmOnTheLetcodeHomepage() {
        UtilityWindow.switchToWindowByIndex(driver,0,log);
        driver.get("https://letcode.in/test");
    }

    @Given("I go to Fake Store test page")
    public void iGoToFakeStoreTestPage() {
        By POMtest = By.xpath("//a[normalize-space()='Page Object Model']");
        UtilityElement.safeClick(driver,POMtest,log);
        UtilityElement.pause(1000,log);
    }

    @And("I get all product titles and prices")
    public void iGetAllProductTitlesAndPrices() {
        UtilityElement.pause(1000,log);
        List<WebElement> products = driver.findElements(By.className("card"));
        for (WebElement product : products) {
            String title = product.findElement(By.cssSelector(".card-header-title")).getText();
            String price = product.findElement(By.cssSelector(".card-footer-item.button.is-link.is-fullwidth")).getText();
            System.out.println(title + " - " + price);
            UtilityElement.logInfo(log, title + " - " + price);

        }
    }

    @And("I click on firts product")
    public void iClickOnFirtsProduct() {
        By product1 = By.xpath("//img[@alt='Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops']");
        UtilityElement.safeClick(driver,product1,log);
    }

    @Then("I get the product detail")
    public void iGetTheProductDetail() {
        By productTitle = By.cssSelector("p[class='title']");
        UtilityValidation.getElementText(driver,productTitle,log);
        By productPrice = By.cssSelector(".subtitle.mt-3");
        UtilityValidation.getElementText(driver,productPrice,log);

    }

    @And("I go back to product page")
    public void iGoBackToProductPage() {
        UtilityNavigation.backPage(driver,log);
        UtilityElement.pause(1000, log);
    }

    @Then("I click on second product")
    public void iClickOnSecondProduct() {
        By product2 = By.xpath("//img[@alt='Mens Casual Premium Slim Fit T-Shirts ']");
        UtilityElement.safeClick(driver,product2,log);

    }

    @When("I go to login page")
    public void iGoToLoginPage() {
        UtilityElement.pause(1000,log);
        By loginButton = By.cssSelector(".fas.fa-user");
        UtilityElement.safeClick(driver,loginButton,log);
    }

    @Then("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String user, String pass) {
        By userBox = By.xpath("//input[@placeholder='Enter Username']");
        UtilityElement.sendKeys(driver,userBox,user,log);

        By passBox = By.xpath("//input[@placeholder='Enter Password']");
        UtilityElement.sendKeys(driver,passBox,pass,log);

        By loginButon = By.xpath("//button[@class='button is-primary']");
        UtilityElement.safeClick(driver,loginButon,log);
    }

    @And("I add three product")
    public void iAddThreeProduct() {
        By addButton = By.xpath("//button[@class='button is-primary mt-4']");



        By product1 = By.cssSelector("img[alt='Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops']");
        UtilityElement.safeClick(driver,product1,log);
        UtilityElement.safeClick(driver,addButton,log);
        UtilityNavigation.backPage(driver,log);

        By product2 = By.cssSelector("img[alt='Mens Casual Premium Slim Fit T-Shirts ']");
        UtilityElement.safeClick(driver,product2,log);
        UtilityElement.safeClick(driver,addButton,log);
        UtilityNavigation.backPage(driver,log);
        By product3 = By.cssSelector("img[alt='Mens Cotton Jacket']");
        UtilityElement.safeClick(driver,product3,log);
        UtilityElement.safeClick(driver,addButton,log);
}

    @When("I go to checkout page")
    public void iGoToCheckoutPage() {
        By cartButton = By.cssSelector("button[class='button is-pulled-right']");
        UtilityElement.safeClick(driver,cartButton,log);
    }

    @Then("I delete the third product")
    public void iDeleteTheThirdProduct() {
        By deleteItem = By.xpath("//tbody/tr[3]/td[5]/button[1]/span[1]/i[1]");
        UtilityElement.safeClick(driver,deleteItem,log);
    }

    @And("I change the quantity of the first product")
    public void iChangeTheQuantityOfTheFirstProduct() {
        By addButton = By.xpath("//tbody/tr[1]/td[2]/button[2]");
        UtilityElement.clickMultipleTime(driver,addButton,3,100,log);

        By priceLocator = By.xpath("//tbody/tr[1]/td[3]");
        String priceText = UtilityValidation.getElementText(driver, priceLocator, log);

        double price = Double.parseDouble(priceText.replace("$", "").trim());

        By totalPriceLocator = By.xpath("//tbody/tr[1]/td[4]");
        String totalPriceText = UtilityValidation.getElementText(driver, totalPriceLocator, log);

        double totalPrice = Double.parseDouble(totalPriceText.replace("$", "").trim());

        double expectedTotal = price * 4;

        System.out.println(expectedTotal + " " + totalPrice);
        UtilityValidation.assertEqualsWithLog(log,expectedTotal,totalPrice);


    }

    @And("I click checkout button")
    public void iClickCheckoutButton() {
        By checkOutButton = By.cssSelector(".button.is-success");
        UtilityElement.safeClick(driver,checkOutButton,log);
        UtilityAlert.acceptAlert(driver,log);
    }

    @Then("I should see {string}")
    public void iShouldSee(String message) {
        By messageString = By.cssSelector(".title.is-4");
        String actualMessage = UtilityValidation.getElementText(driver, messageString, log);
        UtilityValidation.assertEqualsWithLog(log,actualMessage,message);
    }

}
