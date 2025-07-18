package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static utils.UtilityElement.*;

public class UtilityValidation {
    public static void printElementDetails(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                logInfo(log, "Menampilkan detail element " + locator);
                System.out.println("=== Element Details ===");
                System.out.println("Tag Name   : " + element.getTagName());
                System.out.println("Text       : " + element.getText());
                System.out.println("Value      : " + element.getAttribute("value"));
                System.out.println("Type       : " + element.getAttribute("type"));
                System.out.println("Name       : " + element.getAttribute("name"));
                System.out.println("Placeholder: " + element.getAttribute("placeholder"));
                System.out.println("CSS Class  : " + element.getAttribute("class"));
                System.out.println("Displayed  : " + element.isDisplayed());
                System.out.println("Enabled    : " + element.isEnabled());
                System.out.println("Location   : X=" + element.getLocation().getX() + ", Y=" + element.getLocation().getY());
                System.out.println("Size       : W=" + element.getSize().getWidth() + ", H=" + element.getSize().getHeight());
                System.out.println("========================");
            }
        } catch (Exception e) {
            logError(log, "Gagal print detail element " + locator, e);
        }
    }
    public static void validateLinksAndImages(WebDriver driver, PrintWriter log) {
        List<WebElement> elements = new ArrayList<>();
        elements.addAll(driver.findElements(By.tagName("a")));
        elements.addAll(driver.findElements(By.tagName("img")));

        for (WebElement el : elements) {
            String url = el.getTagName().equals("a") ? el.getAttribute("href") : el.getAttribute("src");

            if (url == null || url.trim().isEmpty()) {
                continue;
            }

            String type = el.getTagName().equals("a") ? "LINK" : "IMAGE";

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setConnectTimeout(3000);
                connection.connect();

                int status = connection.getResponseCode();

                if (status == 200) {
                    System.out.println("[VALID " + type + "] " + url);
                    UtilityElement.logInfo(log, "[VALID " + type + "] " + url);
                } else {
                    System.err.println("[BROKEN " + type + "] " + url + " - " + status);
                    UtilityElement.logError(log, "[BROKEN " + type + "] " + url + " - Status: " + status, new Exception("Broken Link"));
                }

            } catch (Exception e) {
                System.err.println("[ERROR " + type + "] " + url + " - " + e.getMessage());
                UtilityElement.logError(log, "[ERROR " + type + "] " + url, e);
            }
        }
    }
    public static String getElementText(WebDriver driver, By locator, PrintWriter log) {
        String text = "";
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                text = element.getText();
                logInfo(log, "Berhasil mengambil text dari element " + locator + " => " + text);
            }
        } catch (Exception e) {
            logError(log, "Gagal mengambil text dari element " + locator, e);
        }
        return text;
    }
    public static void assertEqualsWithLog(PrintWriter log, String expected, String actual) {
        try {
            if (expected.equals(actual)) {
                log.println("[ASSERT PASSED] Expected: \"" + expected + "\" | Actual: \"" + actual + "\"");
            } else {
                log.println("[ASSERT FAILED] Expected: \"" + expected + "\" | Actual: \"" + actual + "\"");
                throw new AssertionError("Assertion Failed! Expected: \"" + expected + "\" | Actual: \"" + actual + "\"");
            }
        } catch (Exception e) {
            log.println("[ASSERT ERROR] " + e.getMessage());
            throw e;
        } finally {
            log.flush();
        }
    }
    public static void assertEqualsWithLog(PrintWriter log, double expected, double actual) {
        double delta = 0.01; // tolerance selisih
        try {
            if (Math.abs(expected - actual) <= delta) {
                log.println("[ASSERT PASSED] Expected: " + expected + " | Actual: " + actual);
            } else {
                log.println("[ASSERT FAILED] Expected: " + expected + " | Actual: " + actual);
                throw new AssertionError("Assertion Failed! Expected: " + expected + " | Actual: " + actual);
            }
        } catch (Exception e) {
            log.println("[ASSERT ERROR] " + e.getMessage());
            throw e;
        } finally {
            log.flush();
        }
    }

    public static void assertEqualsWithLog(PrintWriter log, int expected, int actual) {
        if (expected == actual) {
            logInfo(log, "[ASSERT PASSED] Total expected: " + expected + " | Total displayed: " + actual);
        } else {
            logError(log, "[ASSERT FAILED] Total expected: " + expected + " | Total displayed: " + actual, null);
            throw new AssertionError("Total mismatch! Expected: " + expected + " | Actual: " + actual);
        }
    }

    public static void clickElementInTableCell(
            WebDriver driver,
            By tableLocator,
            int searchColumnIndex,
            String cellValue,
            int targetColumnIndex,
            By innerElementLocator,
            PrintWriter log) {
        try {
            WebElement table = driver.findElement(tableLocator);
            List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() > Math.max(searchColumnIndex, targetColumnIndex)) {
                    String value = cells.get(searchColumnIndex).getText().trim();
                    if (value.equalsIgnoreCase(cellValue)) {
                        WebElement targetCell = cells.get(targetColumnIndex);
                        WebElement elementToClick = targetCell.findElement(innerElementLocator);
                        safeClick(driver, elementToClick, log);
                        logInfo(log, "Klik element " + innerElementLocator + " untuk '" + cellValue + "' di kolom ke-" + targetColumnIndex);
                        return;
                    }
                }
            }
            logInfo(log, "Cell dengan value tidak ditemukan: " + cellValue);
        } catch (Exception e) {
            logError(log, "Gagal klik element " + innerElementLocator + " untuk cell '" + cellValue + "'", e);
        }
    }


}
