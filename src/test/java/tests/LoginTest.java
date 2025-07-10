package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test
    public void testLoginPageLoadPerformance() {
        test = extent.createTest("Test Case 1: Page Load Performance");
        logger.info("Starting Test Case 1: Page Load Performance");

        LoginPage loginPage = new LoginPage(driver);

        long startTime = System.currentTimeMillis();
        loginPage.waitForLoginElements();
        long endTime = System.currentTimeMillis();

        double loadTime = (endTime - startTime) / 1000.0;
        test.log(Status.INFO, "Time taken: " + loadTime + " seconds");
        logger.info("Page load time: {} seconds", loadTime);

        if (loadTime <= 30) {
            test.pass("Login elements loaded within acceptable time.");
            logger.info("Login page loaded successfully within threshold.");
        } else {
            test.fail("Page load too slow.");
            logger.error("Login page took too long to load.");
        }

        Assert.assertTrue(loadTime <= 30, "Page load exceeded limit");
    }

    @Test
    public void testInvalidLoginValidation() {
        test = extent.createTest("Test Case 2: Invalid Login Validation");
        logger.info("Starting Test Case 2: Invalid Login Validation");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();
        logger.info("Login elements are visible.");

        loginPage.performLogin("9999999999", "WrongPassword");
        logger.info("Performed login with invalid credentials.");

        boolean errorShown = loginPage.isErrorMessageDisplayed();
        test.log(Status.INFO, "Error message visible: " + errorShown);
        logger.info("Error message displayed: {}", errorShown);

        if (errorShown) {
            test.pass("Proper error message displayed.");
            logger.info("Error message validation passed.");
        } else {
            test.fail("Error message not displayed.");
            logger.error("Error message validation failed.");
        }

        Assert.assertTrue(errorShown, "Expected error not shown");
    }

    @Test
    public void testForgotPasswordFeatureValidation() {
        test = extent.createTest("Test Case 3: Forgot Password Flow");
        logger.info("Starting Test Case 3: Forgot Password Flow");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();
        logger.info("Login elements are visible.");

        loginPage.clickForgotPassword();
        logger.info("Clicked on Forgot Password link.");

        loginPage.enterForgotUsername("9999999999");
        logger.info("Entered username for Forgot Password.");

        loginPage.clickSendButton();
        logger.info("Clicked Send button in Forgot Password flow.");

        boolean messageShown = loginPage.isUsernameDoesNotExistMessageDisplayed();
        test.log(Status.INFO, "Forgot Password message: " + messageShown);
        logger.info("Forgot Password message displayed: {}", messageShown);

        if (messageShown) {
            test.pass("Correct forgot password message shown.");
            logger.info("Forgot Password validation passed.");
        } else {
            test.fail("Forgot password message missing.");
            logger.error("Forgot Password validation failed.");
        }

        Assert.assertTrue(messageShown, "Forgot Password message not visible");
    }

    @Test
    public void testInstagramRedirect() {
        test = extent.createTest("Test Case 4: Instagram Redirect");
        logger.info("Starting Test Case 4: Instagram Redirect");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();
        logger.info("Login elements are visible.");

        if (!loginPage.isForgotPasswordVisible()) {
            loginPage.clickInstagramIcon();
            logger.info("Clicked Instagram icon.");

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            String url = driver.getCurrentUrl();
            test.log(Status.INFO, "Redirected to: " + url);
            logger.info("Redirected URL: {}", url);

            if (url.contains("instagram.com")) {
                test.pass("Redirected to Instagram successfully.");
                logger.info("Instagram redirect validation passed.");
            } else {
                test.fail("Not redirected to Instagram.");
                logger.error("Instagram redirect validation failed.");
            }

            Assert.assertTrue(url.contains("instagram.com"), "Instagram redirect failed");
        } else {
            test.skip("Forgot Password is visible. Skipping Instagram test.");
            logger.warn("Forgot Password link is visible—skipping Instagram test.");
        }
    }
}
