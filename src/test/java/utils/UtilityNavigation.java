package utils;

import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;

import static utils.UtilityElement.logError;
import static utils.UtilityElement.logInfo;

public class UtilityNavigation {
    public static void refreshPage(WebDriver driver, PrintWriter log) {
        try {
            driver.navigate().refresh();
            logInfo(log, "Melakukan refresh halaman");
        } catch (Exception e) {
            logError(log, "Gagal melakukan refresh halaman", e);
        }
    }

    public static void backPage(WebDriver driver, PrintWriter log) {
        try {
            driver.navigate().back();
            logInfo(log, "Melakukan navigasi back");
        } catch (Exception e) {
            logError(log, "Gagal melakukan navigasi back", e);
        }
    }
}
