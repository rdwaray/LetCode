package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;

import static utils.driverFactory.driver;

public class UtilityElement {

    private static final int DEFAULT_TIMEOUT = 800;

    public static void logError(PrintWriter log, String message, Exception e) {
        System.err.println("[ERROR] " + message + ": " + e.getMessage());
        log.println("[ERROR] " + message + ": " + e.getMessage());
        log.flush();
    }

    public static void logInfo(PrintWriter log, String message) {
        System.out.println("[INFO] " + message);
        log.println("[INFO] " + message);
        log.flush();
    }

    public static void pause(int timeoutMillis, PrintWriter log) {
        try {
            logInfo(log, "Delay selama " + timeoutMillis + " ms");
            Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            logError(log, "Gagal delay", e);
            Thread.currentThread().interrupt();
        }
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutMillis, PrintWriter log) {
        try {
            logInfo(log, "Menunggu element " + locator);
            WebElement element = new WebDriverWait(driver, Duration.ofMillis(timeoutMillis))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            logInfo(log, "Element ditemukan " + locator);
            return element;
        } catch (Exception e) {
            logError(log, "Gagal menemukan element " + locator, e);
            return null;
        }
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, PrintWriter log) {
        return waitForElementVisible(driver, locator, DEFAULT_TIMEOUT, log);
    }

    public static WebElement findElement(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                logInfo(log, "Element di-scroll ke view " + locator);
            }
            return element;
        } catch (Exception e) {
            logError(log, "Gagal scroll element " + locator, e);
            return null;
        }
    }

    public static void sendKeys(WebDriver driver, By locator, String text, PrintWriter log) {
        try {
            WebElement input = waitForElementVisible(driver, locator, log);
            if (input != null) {
                input.clear();
                input.sendKeys(text);
                logInfo(log, "Mengisi teks ke element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal mengisi teks ke element " + locator, e);
        }
    }

    public static void sendKeysWithJS(WebDriver driver, By locator, String text, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                String script = "arguments[0].value='" + text + "';";
                ((JavascriptExecutor) driver).executeScript(script, element);
                logInfo(log, "Mengisi teks dengan JS ke element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal mengisi teks dengan JS ke element " + locator, e);
        }
    }

    public static void sendKeysAndKeyButton(WebDriver driver, By locator, String text, Keys key, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                element.clear();
                element.sendKeys(text);
                element.sendKeys(key);
                logInfo(log, "Mengisi teks dan menekan tombol pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal input teks dan key pada element " + locator, e);
        }
    }

    public static void pressKeyButton(WebDriver driver, By locator, Keys key, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                element.sendKeys(key);
                logInfo(log, "Menekan tombol pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal menekan tombol pada element " + locator, e);
        }
    }

    public static void safeClick(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                element.click();
                logInfo(log, "Klik element " + locator);
            }
        } catch (Exception e) {
            try {
                WebElement element = waitForElementVisible(driver, locator, log);
                if (element != null) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                    logInfo(log, "Klik dengan JS pada element " + locator);
                }
            } catch (Exception ex) {
                logError(log, "Gagal klik element dengan JS " + locator, ex);
            }
        }
    }

    public static void doubleClick(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                new Actions(driver).doubleClick(element).perform();
                logInfo(log, "Double click element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal double click element " + locator, e);
        }
    }

    public static void clickMultipleTime(WebDriver driver, By locator, int times, int delayMs, PrintWriter log) {
        for (int i = 0; i < times; i++) {
            safeClick(driver, locator, log);
            pause(delayMs, log);
        }
    }

    public static void rightClickWithJS(WebDriver driver, By locator, PrintWriter log) {
        try {
            WebElement element = waitForElementVisible(driver, locator, log);
            if (element != null) {
                String js = """
                        var evt = new MouseEvent('contextmenu', {
                            bubbles: true,
                            cancelable: true,
                            view: window,
                            button: 2
                        });
                        arguments[0].dispatchEvent(evt);
                        """;
                ((JavascriptExecutor) driver).executeScript(js, element);
                logInfo(log, "Right click JS pada element " + locator);
            }
        } catch (Exception e) {
            logError(log, "Gagal right click dengan JS " + locator, e);
        }
    }

    public static void dragAndDrop(WebDriver driver, By sourceLocator, By targetLocator, PrintWriter log) {
        try {
            WebElement source = waitForElementVisible(driver, sourceLocator, log);
            WebElement target = waitForElementVisible(driver, targetLocator, log);
            if (source != null && target != null) {
                new Actions(driver).dragAndDrop(source, target).perform();
                logInfo(log, "Drag dari " + sourceLocator + " ke " + targetLocator);
            }
        } catch (Exception e) {
            logError(log, "Gagal drag dari " + sourceLocator + " ke " + targetLocator, e);
        }
    }

    public static void clickAndHold(WebDriver driver, By sourceLocator, By targetLocator, PrintWriter log) {
        try {
            WebElement source = waitForElementVisible(driver, sourceLocator, log);
            WebElement target = waitForElementVisible(driver, targetLocator, log);
            if (source != null && target != null) {
                new Actions(driver).clickAndHold(source).moveToElement(target).release().perform();
                logInfo(log, "Click and hold dari " + sourceLocator + " ke " + targetLocator);
            }
        } catch (Exception e) {
            logError(log, "Gagal click and hold dari " + sourceLocator + " ke " + targetLocator, e);
        }
    }

    public static void resizeElement(WebDriver driver, By resizerLocator, int xOffset, int yOffset, PrintWriter log) {
        try {
            WebElement resizer = waitForElementVisible(driver, resizerLocator, log);
            if (resizer != null) {
                new Actions(driver).clickAndHold(resizer).moveByOffset(xOffset, yOffset).release().perform();
                logInfo(log, "Resize element " + resizerLocator + " sebesar X=" + xOffset + ", Y=" + yOffset);
            }
        } catch (Exception e) {
            logError(log, "Gagal resize element " + resizerLocator, e);
        }
    }

    public static void checkImageStatus(String imageUrl, PrintWriter log) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("[VALID IMAGE] " + imageUrl);
                UtilityElement.logInfo(log, "[VALID IMAGE] " + imageUrl);
            } else {
                System.err.println("[BROKEN IMAGE] " + imageUrl + " - Status: " + responseCode);
                UtilityElement.logError(log, "[BROKEN IMAGE] " + imageUrl + " - Status: " + responseCode, new Exception("Broken Image"));
            }
        } catch (Exception e) {
            System.err.println("[ERROR IMAGE] " + imageUrl + " - " + e.getMessage());
            UtilityElement.logError(log, "[ERROR IMAGE] " + imageUrl, e);
        }
    }

    public static void checkLinkStatus(String linkUrl, PrintWriter log) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(linkUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("[VALID LINK] " + linkUrl);
                UtilityElement.logInfo(log, "[VALID LINK] " + linkUrl);
            } else {
                System.err.println("[BROKEN LINK] " + linkUrl + " - Status: " + responseCode);
                UtilityElement.logError(log, "[BROKEN LINK] " + linkUrl + " - Status: " + responseCode, new Exception("Broken Link"));
            }
        } catch (Exception e) {
            System.err.println("[ERROR LINK] " + linkUrl + " - " + e.getMessage());
            UtilityElement.logError(log, "[ERROR LINK] " + linkUrl, e);
        }
    }
    public static void validateAllImages(WebDriver driver, PrintWriter log) {
        List<WebElement> images = driver.findElements(By.tagName("img"));
        for (WebElement img : images) {
            String src = img.getAttribute("src");
            if (src != null && !src.trim().isEmpty()) {
                checkImageStatus(src, log);
            }
        }
    }

    public static void validateAllLinks(WebDriver driver, PrintWriter log) {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.trim().isEmpty()) {
                checkLinkStatus(href, log);
            }
        }
    }

    public static void validateRowLength(String[] row, int expectedLength, int rowIndex, PrintWriter log) {
        if (row.length != expectedLength) {
            String message = "Row ke-" + rowIndex + " tidak valid. Expected " + expectedLength + " kolom, tapi dapat " + row.length;
            System.err.println("[ERROR] " + message);
            UtilityElement.logError(log, message, new Exception("Invalid CSV Row Length"));
        } else {
            UtilityElement.logInfo(log, "Row ke-" + rowIndex + " valid dengan " + row.length + " kolom.");
        }
    }

    public static void takeScreenshot(WebDriver driver, PrintWriter log) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            File dest = new File("screenshots/screenshot_" + timestamp + ".png");
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to: " + dest.getAbsolutePath());
            log.println("[INFO] Screenshot saved to: " + dest.getAbsolutePath());
            log.flush();
        } catch (IOException e) {
            logError(log, "Gagal mengambil screenshot", e);
        }
    }

    public static void safeRefresh(WebDriver driver, int delayAfterMs, PrintWriter log) {
        try {
            logInfo(log, "Melakukan refresh halaman");
            driver.navigate().refresh();
            pause(delayAfterMs, log);
        } catch (Exception e) {
            logError(log, "Gagal refresh halaman", e);
        }
    }

    public static void longPress(WebDriver driver, By locator, int durationMillis, PrintWriter log) {
        try {
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            UtilityElement.logInfo(log, "Melakukan long press pada element: " + locator);
            actions.clickAndHold(element)
                    .pause(Duration.ofMillis(durationMillis))
                    .release()
                    .perform();
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal melakukan long press pada element: " + locator, e);
        }
    }

    public static void dragAndDropByOffset(WebDriver driver, WebElement element, int xOffset, int yOffset, PrintWriter log) {
        try {
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(element, xOffset, yOffset).perform();
            log.println("[INFO] Berhasil drag element " + element.toString() + " sejauh X=" + xOffset + " Y=" + yOffset);
            log.flush();
        } catch (Exception e) {
            log.println("[ERROR] Gagal drag element " + element.toString() + " karena " + e.getMessage());
            log.flush();
        }
    }
//    public static void dragAndDropCDK(WebDriver driver, WebElement source, WebElement target, PrintWriter log) {
//        try {
//            String script = "const src = arguments[0];" +
//                    "const tgt = arguments[1];" +
//                    "const dataTransfer = new DataTransfer();" +
//                    "src.dispatchEvent(new DragEvent('dragstart', { dataTransfer }));" +
//                    "tgt.dispatchEvent(new DragEvent('drop', { dataTransfer }));" +
//                    "src.dispatchEvent(new DragEvent('dragend', { dataTransfer }));";
//            ((JavascriptExecutor) driver).executeScript(script, source, target);
//            log.println("[INFO] Berhasil drag element (CDK) dari " + source.toString() + " ke " + target.toString());
//            log.flush();
//        } catch (Exception e) {
//            log.println("[ERROR] Gagal drag element (CDK) dari " + source.toString() + " ke " + target.toString() + " karena " + e.getMessage());
//            log.flush();
//        }
//    }
    public static void dragAndDropCDK(WebDriver driver, By sourceLocator, By targetLocator, PrintWriter log) {
        try {
            WebElement source = waitForElementVisible(driver, sourceLocator, log);
            WebElement target = waitForElementVisible(driver, targetLocator, log);
            if (source != null && target != null) {
                String script = "const src = arguments[0];" +
                        "const tgt = arguments[1];" +
                        "const dataTransfer = new DataTransfer();" +
                        "src.dispatchEvent(new DragEvent('dragstart', { dataTransfer }));" +
                        "tgt.dispatchEvent(new DragEvent('drop', { dataTransfer }));" +
                        "src.dispatchEvent(new DragEvent('dragend', { dataTransfer }));";
                ((JavascriptExecutor) driver).executeScript(script, source, target);
                logInfo(log, "Drag and Drop (CDK) dari " + sourceLocator + " ke " + targetLocator);
            }
        } catch (Exception e) {
            logError(log, "Gagal Drag and Drop (CDK) dari " + sourceLocator + " ke " + targetLocator, e);
        }
    }
    public static void safeClick(WebDriver driver, WebElement element, PrintWriter log) {
        try {
            if (element != null && element.isDisplayed() && element.isEnabled()) {
                element.click();
                logInfo(log, "Klik element langsung: " + element);
            } else {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                logInfo(log, "Klik element pakai JS: " + element);
            }
        } catch (Exception e) {
            logError(log, "Gagal klik element langsung: " + element, e);
        }
    }



}
