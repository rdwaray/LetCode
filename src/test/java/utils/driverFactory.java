package utils;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.network.Network;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

    public class driverFactory {
        private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
        private static ThreadLocal<DevTools> devTools = new ThreadLocal<>();
        private static ThreadLocal<PrintWriter> log = new ThreadLocal<>();
        private static ThreadLocal<PrintWriter> csvLog = new ThreadLocal<>();
        private static ThreadLocal<Faker> faker = new ThreadLocal<>();
        private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

        public static WebDriver getDriver() {
            if (driver.get() == null) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-features=PasswordManagerEnabled,AutofillServerCommunication");

                options.setExperimentalOption("prefs", Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false
                ));

                ChromeDriver chromeDriver = new ChromeDriver(options);
                driver.set(chromeDriver);

                DevTools devTool = chromeDriver.getDevTools();
                devTool.createSession();
                devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                devTool.send(Network.setBlockedURLs(List.of(
                        "*doubleclick.net*", "*adsystem.com*", "*googlesyndication.com*",
                        "*popads.net*", "*adservice.google.com*"
                )));
                devTools.set(devTool);
            }
            return driver.get();
        }

        public static Faker getFaker() {
            if (faker.get() == null) {
                faker.set(new Faker());
            }
            return faker.get();
        }

        public static WebDriverWait getWait() {
            if (wait.get() == null && driver.get() != null) {
                wait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(10)));
            }
            return wait.get();
        }

        public static PrintWriter getLog() {
            if (log.get() == null) {
                try {
                    String baseFolder = "src/test/resources/data/output/";
                    String csvFolder = baseFolder + "csv/";
                    new java.io.File(baseFolder).mkdirs();
                    new java.io.File(csvFolder).mkdirs();

                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                    PrintWriter fileLog = new PrintWriter(baseFolder + "log_" + timestamp + ".txt");
                    PrintWriter fileCSV = new PrintWriter(csvFolder + "log_" + timestamp + ".txt");

                    log.set(fileLog);
                    csvLog.set(fileCSV);

                    System.out.println("[INFO] Log created at: " + new java.io.File(baseFolder + "log_" + timestamp + ".txt").getAbsolutePath());
                    System.out.println("[INFO] CSV Log created at: " + new java.io.File(csvFolder + "log_" + timestamp + ".txt").getAbsolutePath());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return log.get();
        }

        public static PrintWriter getCSVLog() {
            if (csvLog.get() == null) {
                getLog();
            }
            return csvLog.get();
        }

        public static void writeBothLog(String message) {
            if (log.get() != null) {
                log.get().println(message);
                log.get().flush();
            }
            if (csvLog.get() != null) {
                csvLog.get().println(message);
                csvLog.get().flush();
            }
        }

        public static void quitDriver() {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
            devTools.remove();
            if (log.get() != null) {
                log.get().close();
                log.remove();
            }
            if (csvLog.get() != null) {
                csvLog.get().close();
                csvLog.remove();
            }
            faker.remove();
            wait.remove();
        }
    }