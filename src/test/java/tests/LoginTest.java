package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginPageLoadPerformance() throws InterruptedException {
        test = extent.createTest("Test Case 1: Page Load Performance");

        LoginPage loginPage = new LoginPage(driver);

        long startTime = System.currentTimeMillis();
        loginPage.waitForLoginElements();
        long endTime = System.currentTimeMillis();

        double loadTime = (endTime - startTime) / 1000.0;
        test.log(Status.INFO, "Time taken: " + loadTime + " seconds");

        if (loadTime <= 30) {

            test.pass("Login elements loaded within acceptable time.");
        } else {
            test.fail("Page load too slow.");
        }

        Assert.assertTrue(loadTime <= 30, "Page load exceeded limit");
    }

    @Test
    public void testInvalidLoginValidation() throws InterruptedException {
        test = extent.createTest("Test Case 2: Invalid Login Validation");



        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();
        loginPage.performLogin("9999999999", "WrongPassword");

        boolean errorShown = loginPage.isErrorMessageDisplayed();
        test.log(Status.INFO, "Error message visible: " + errorShown);

        if (errorShown) {
            test.pass("Proper error message displayed.");
        } else {
            test.fail("Error message not displayed.");
        }

        Assert.assertTrue(errorShown, "Expected error not shown");
    }

    @Test
    public void testForgotPasswordFeatureValidation() throws InterruptedException {
        test = extent.createTest("Test Case 3: Forgot Password Flow");



        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();
        loginPage.clickForgotPassword();
        loginPage.enterForgotUsername("9999999999");
        loginPage.clickSendButton();

        boolean messageShown = loginPage.isUsernameDoesNotExistMessageDisplayed();
        test.log(Status.INFO, "Forgot Password message: " + messageShown);

        if (messageShown) {
            test.pass("Correct forgot password message shown.");
        } else {
            test.fail("Forgot password message missing.");
        }

        Assert.assertTrue(messageShown, "Forgot Password message not visible");
    }

    @Test
    public void testInstagramRedirect() throws InterruptedException {
        test = extent.createTest("Test Case 4: Instagram Redirect");



        LoginPage loginPage = new LoginPage(driver);

        loginPage.waitForLoginElements();

        if (!loginPage.isForgotPasswordVisible()) {
            loginPage.clickInstagramIcon();

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }

            String url = driver.getCurrentUrl();
            test.log(Status.INFO, "Redirected to: " + url);

            if (url.contains("instagram.com")) {
                test.pass("Redirected to Instagram successfully.");
            } else {
                test.fail("Not redirected to Instagram.");
            }

            Assert.assertTrue(url.contains("instagram.com"), "Instagram redirect failed");
        } else {
            test.skip("Forgot Password is visible. Skipping Instagram test.");
        }
    }
}
