package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.PrintWriter;

import static utils.UtilityElement.*;

public class UtilityDropdown {
    private static final int DEFAULT_TIMEOUT = 800;

    public static void selectDropDownByText(WebDriver driver, By locator, String text, PrintWriter log) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, log);
            if (dropdown != null) {
                new Select(dropdown).selectByVisibleText(text);
                logInfo(log, "Select dropdown by text '" + text + "' pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal select dropdown by text '" + text + "' pada element " + locator, e);
        }
    }

    public static void selectDropDownByValue(WebDriver driver, By locator, String value, PrintWriter log) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, log);
            if (dropdown != null) {
                new Select(dropdown).selectByValue(value);
                logInfo(log, "Select dropdown by value '" + value + "' pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal select dropdown by value '" + value + "' pada element " + locator, e);
        }
    }

    public static void selectDropDownByIndex(WebDriver driver, By locator, int index, PrintWriter log) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, log);
            if (dropdown != null) {
                new Select(dropdown).selectByIndex(index);
                logInfo(log, "Select dropdown by index '" + index + "' pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal select dropdown by index '" + index + "' pada element " + locator, e);
        }
    }

    public static void printDropDownOptions(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, log);
            if (dropdown != null) {
                Select select = new Select(dropdown);
                logInfo(log, "=== Options dari dropdown " + locator + " ===");
                for (WebElement option : select.getOptions()) {
                    String optionText = option.getText();
                    System.out.println(optionText);
                    log.println("[OPTION] " + optionText);
                }
                log.flush();
            }
        } catch (Exception e) {
            logError(log, "Gagal mengambil options dari dropdown " + locator, e);
        }
    }

}
