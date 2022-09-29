package com.woa.core;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.ChronoUnit.SECONDS;

public class TestBase {
    public static WebDriver driver;
    private static ExtentReports extent;

    // store the creds in the properties file and read from there
    private final String BROWSERSTACK_USERNAME = "sakibuddin_gvjaqB";
    private final String BROWSERSTACK_ACCESS_KEY = "u8XqzKEZprFn3nLQkJVe";
    private final String URL_OF_BS = "https://" + BROWSERSTACK_USERNAME + ":" + BROWSERSTACK_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private final String SAUCE_USERNAME = "oauth-uddinsakib430-0c01a";
    private final String SAUCE_ACCESS_KEY = "f59de7c6-443f-4f4c-8d30-bbad27725529";
    private final String URL_OF_SAUCE = "https://oauth-uddinsakib430-0c01a:f59de7c6-443f-4f4c-8d30-bbad27725529@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    public static void waitTillClickable(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitFor(int seconds) {
        try {
            // static sleep
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    @Parameters({"browserName", "url", "cloud", "os", "sauce"})
    public void setupBrowser(String browserName, String url, boolean cloud, String os, boolean sauce, Method method) throws MalformedURLException {
        if (cloud) {
            driver = getRemoteWebDriver(sauce);
        } else {
            driver = getLocalWebDriver(browserName, os);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.of(20, SECONDS));
        driver.manage().timeouts().implicitlyWait(Duration.of(20, SECONDS));
        driver.get(url);
    }

    private WebDriver getLocalWebDriver(String browserName, String os) {
        if (browserName.equalsIgnoreCase("chrome")) {
            if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else {
            if (os.equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        return driver;
    }

    private WebDriver getRemoteWebDriver(boolean sauce) throws MalformedURLException {
        if(sauce) {
            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("Windows 11");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "<Test1>");
            sauceOptions.put("name", "<Automation Tests>");
            browserOptions.setCapability("sauce:options", sauceOptions);

            URL url = new URL("https://oauth-uddinsakib430-0c01a:f59de7c6-443f-4f4c-8d30-bbad27725529@ondemand.us-west-1.saucelabs.com:443/wd/hub");
             driver = new RemoteWebDriver(url, browserOptions);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "Firefox");
            capabilities.setCapability("browserVersion", "89");

            HashMap<String, Object> browserstackOptions = new HashMap<>();
            browserstackOptions.put("os", "OS X");
            browserstackOptions.put("osVersion", "Sierra");

            capabilities.setCapability("bstack:options", browserstackOptions);

             driver = new RemoteWebDriver(new URL(URL_OF_BS), capabilities);
        }
        return driver;
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();
    }

    public void scrollDownToSpecificElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //Extent Report Setup
    @BeforeSuite(alwaysRun = true)
    public void extentSetup(ITestContext context) {
        ExtentTestManager.setOutputDirectory(context);
        extent = ExtentTestManager.getInstance();
    }

    //Extent Report Setup for each methods
    @BeforeMethod(alwaysRun = true)
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    //Extent Report cleanup for each methods
    @AfterMethod(alwaysRun = true)
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(ExtentTestManager.getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(ExtentTestManager.getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "TEST CASE PASSED : " + result.getName());
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED : " + result.getName() + " :: " + ExtentTestManager.getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST CASE SKIPPED : " + result.getName());
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.captureScreenshot(driver, result.getName());
        }
    }

    //Extent Report closed
    @AfterSuite(alwaysRun = true)
    public void generateReport() {
        extent.close();
    }

}
