package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Timestamp for Unique Report File
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/test-output/Reports/ExtentReport_" + timestamp + ".html";

            // Create Spark Reporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Reporter Configurations
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Abhay Nayak QA Automation Report");
            sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

            // Create ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add System Information
            extent.setSystemInfo("Project Name", "Merchant Portal");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Abhay Nayak");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }
}
