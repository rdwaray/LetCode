package utils;

import org.openqa.selenium.*;

import java.io.PrintWriter;

import static utils.UtilityElement.logError;
import static utils.UtilityElement.logInfo;

public class UtilityShadow {

    public static WebElement findElementInOpenShadowRoot(WebDriver driver, By shadowHostLocator, By insideShadowLocator, PrintWriter log) {
        try {
            logInfo(log, "Mencari Shadow Host: " + shadowHostLocator);
            WebElement shadowHost = driver.findElement(shadowHostLocator);

            logInfo(log, "Mengambil Open Shadow Root dari host");
            SearchContext shadowRoot = shadowHost.getShadowRoot();

            WebElement elementInsideShadow = shadowRoot.findElement(insideShadowLocator);
            logInfo(log, "Element dalam Shadow DOM ditemukan: " + insideShadowLocator);
            return elementInsideShadow;
        } catch (Exception e) {
            logError(log, "Gagal mencari element dalam Open Shadow DOM", e);
            return null;
        }
    }
    public static WebElement findElementInClosedShadowRootJS(WebDriver driver, String shadowHostQuery, String insideShadowQuery, PrintWriter log) {
        try {
            logInfo(log, "Mencari element dalam Closed Shadow DOM dengan JS");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = (WebElement) js.executeScript(
                    "return document.querySelector('" + shadowHostQuery + "')" +
                            ".shadowRoot.querySelector('" + insideShadowQuery + "')");
            logInfo(log, "Element berhasil ditemukan via JS");
            return element;
        } catch (Exception e) {
            logError(log, "Gagal mencari element Closed Shadow DOM dengan JS", e);
            return null;
        }
    }
}
