package utils;

import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UtilityWindow {

    public static void switchToWindowByIndex(WebDriver driver, int index, PrintWriter log) {
        try {
            List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
            if (index < 0 || index >= windowHandles.size()) {
                throw new RuntimeException("Index tab di luar jangkauan: " + index);
            }
            driver.switchTo().window(windowHandles.get(index));
            UtilityElement.logInfo(log, "Berhasil switch ke window index ke-" + index);
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch ke window index " + index, e);
        }
    }

    public static void switchToDefaultTab(WebDriver driver, PrintWriter log) {
        try {
            String defaultHandle = driver.getWindowHandles().iterator().next();
            driver.switchTo().window(defaultHandle);
            UtilityElement.logInfo(log, "Berhasil switch ke default window");
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal switch ke default window", e);
        }
    }

    public static void printAllWindowTitles(WebDriver driver, PrintWriter log) {
        try {
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();
                System.out.println("Window title: " + title);
                UtilityElement.logInfo(log, "Window title: " + title);
            }
        } catch (Exception e) {
            UtilityElement.logError(log, "Gagal mengambil judul semua window", e);
        }
    }

    public static void closeCurrentTab(WebDriver driver, PrintWriter log) {
        try {
            driver.close();
            log.println("[INFO] Closed current tab.");
            log.flush();
        } catch (Exception e) {
            log.println("[ERROR] Failed to close current tab: " + e.getMessage());
            log.flush();
        }
    }


    public static void closeTabByIndex(WebDriver driver, int index, PrintWriter log) {
        try {
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            if (index < tabs.size()) {
                driver.switchTo().window(tabs.get(index));
                driver.close();
                log.println("[INFO] Closed tab at index: " + index);
            } else {
                log.println("[WARN] Cannot close tab. Index " + index + " is out of bounds.");
            }
            log.flush();
        } catch (Exception e) {
            log.println("[ERROR] Failed to close tab by index: " + e.getMessage());
            log.flush();
        }
    }
}

