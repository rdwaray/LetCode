package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;

public class UtilityFrame {
    // Switch ke frame by index
    public static void switchToFrame(WebDriver driver, int index, PrintWriter log) {
        try {
            driver.switchTo().frame(index);
            UtilityElement.logInfo(log, "Switch to frame index: " + index);
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch to frame index: " + index, e);
        }
    }

    // Switch ke frame by name/id
    public static void switchToFrame(WebDriver driver, String nameOrId, PrintWriter log) {
        try {
            driver.switchTo().frame(nameOrId);
            UtilityElement.logInfo(log, "Switch to frame name/id: " + nameOrId);
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch to frame name/id: " + nameOrId, e);
        }
    }

    // Switch ke frame by WebElement
    public static void switchToFrame(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement frameElement = driver.findElement(locator);
            driver.switchTo().frame(frameElement);
            UtilityElement.logInfo(log, "Switch to frame element: " + locator);
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch to frame element: " + locator, e);
        }
    }

    // Switch ke default content
    public static void switchToDefaultContent(WebDriver driver, PrintWriter log) {
        try {
            driver.switchTo().defaultContent();
            UtilityElement.logInfo(log, "Switch to default content");
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch to default content", e);
        }
    }

    // Switch ke parent frame
    public static void switchToParentFrame(WebDriver driver, PrintWriter log) {
        try {
            driver.switchTo().parentFrame();
            UtilityElement.logInfo(log, "Switch to parent frame");
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch to parent frame", e);
        }
    }

}
