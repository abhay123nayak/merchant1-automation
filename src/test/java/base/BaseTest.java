package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.ExtentManager;

public class BaseTest {

    public WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("linux")) {
            // For GitHub Actions (Ubuntu runners)
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--user-data-dir=/tmp/chrome-user-data");
        } else {
            // For Windows 11 (your local machine)
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
        driver.get("https://merchant1.uatdev.in/auth/login");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
