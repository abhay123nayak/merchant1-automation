package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static final Logger logger = LogManager.getLogger(ExtentManager.class);
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            logger.info("Creating new ExtentReports instance.");

            // Timestamp for Unique Report File
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/Reports/ExtentReport_" + timestamp + ".html";

            logger.info("Report will be generated at path: {}", reportPath);

            // Create Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Reporter Configurations
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Abhay Nayak QA Automation Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            logger.info("Configured ExtentSparkReporter with theme, title, name, and timestamp.");

            // Create ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add System Information
            extent.setSystemInfo("Project Name", "Merchant Portal");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Abhay Nayak");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));

            logger.info("System information added to Extent Report.");
        } else {
            logger.info("Reusing existing ExtentReports instance.");
        }
        return extent;
    }
}
