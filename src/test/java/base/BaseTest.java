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
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        // Common options for all platforms
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows 11 or any Windows OS
            options.addArguments("--start-maximized");
        } else {
            // Linux (GitHub Actions etc.)
            options.addArguments("--headless=new");  // Use headless=new for Chrome 109+
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--user-data-dir=/tmp/chrome-user-data");
        }

        driver = new ChromeDriver(options);
        driver.get("https://merchant1.uatdev.in/auth/login");
        Thread.sleep(10);
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
