package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.io.PrintWriter;

import static utils.UtilityElement.logError;
import static utils.UtilityElement.logInfo;

public class UtilityAlert {
    private static final int DEFAULT_TIMEOUT = 800;


    public static void acceptAlert(WebDriver driver, PrintWriter log) {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            logInfo(log, "Accept alert dengan pesan: '" + alertText + "'");
        } catch (NoAlertPresentException e) {
            logError(log, "Tidak ada alert yang muncul", e);
        } catch (Exception e) {
            logError(log, "Gagal accept alert", e);
        }
    }

    public static void dismissAlert(WebDriver driver, PrintWriter log){
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            alert.dismiss();
            logInfo(log, "Dismiss alert dengan pesan: '" + alertText + "'");
        } catch (NoAlertPresentException e) {
            logError(log, "Tidak ada alert yang muncul", e);
        } catch (Exception e) {
            logError(log, "Gagal dismiss alert", e);
        }
    }

    public static void getAlertText(WebDriver driver,PrintWriter log){
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            logInfo(log, "Isi text alert: '" + alertText + "'");
        } catch (NoAlertPresentException e) {
            logError(log, "Tidak ada alert yang muncul", e);
        } catch (Exception e) {
            logError(log, "Gagal ambil text alert", e);
        }
    }

    public static void sendKeysToAlert(WebDriver driver, String inputText, PrintWriter log) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(inputText);
            String alertText = alert.getText();
            alert.accept();
            logInfo(log, "Kirim dan accept alert dengan input: '" + inputText + "', isi alert: '" + alertText + "'");
        } catch (NoAlertPresentException e) {
            logError(log, "Tidak ada alert yang muncul saat ingin mengisi input", e);
        } catch (Exception e) {
            logError(log, "Gagal mengisi input dan accept alert", e);
        }
    }
}
