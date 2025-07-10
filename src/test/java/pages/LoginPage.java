package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestUtils;

import static utils.TestUtils.sleep;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='fullName']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(text(),'Invalid username or password')]")
    private WebElement errorMessageToaster;

    @FindBy(xpath = "//a[contains(text(),'Forgot Password ?')]")
    private WebElement forgotPasswordLink;

    @FindBy(xpath = "//a[contains(text(),'39482309Forgot Password ?')]")
    private WebElement forgotPasswordLink_1;

    @FindBy(xpath = "//input[@id='fullName']")  // Assuming same username field appears on forgot page
    private WebElement forgotUsernameInput;

    @FindBy(xpath = "//button[contains(text(),'Send')]")
    private WebElement sendButton;

    @FindBy(xpath = "//div[contains(text(),'Username not exists')]")
    private WebElement usernameNotExistMessage;

    @FindBy(xpath = "//ul[@class='social-media']/div/li/a/i[@class='fa-brands fa-instagram']")
    private WebElement instagramIcon;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Wait for login page elements (already used in first test)
    public void waitForLoginElements() {
        sleep(2);
        TestUtils.isVisible(driver, usernameInput);
        TestUtils.isVisible(driver, passwordInput);
        TestUtils.isVisible(driver, loginButton);
    }

    // Enter username
    public void enterUsername(String username) {
        usernameInput.clear();
        sleep(2);
        usernameInput.sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        passwordInput.clear();
        sleep(2);
        passwordInput.sendKeys(password);
    }

    // Click Login button
    public void clickLogin() {
        sleep(2);
        loginButton.click();
    }

    // Check if error message is visible
    public boolean isErrorMessageDisplayed() {
        return TestUtils.isVisible(driver, errorMessageToaster);
    }

    // Complete invalid login flow
    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void clickForgotPassword() {
        sleep(4);
        forgotPasswordLink.click();
    }

    public void enterForgotUsername(String username) {
        sleep(3);
        forgotUsernameInput.clear();
        forgotUsernameInput.sendKeys(username);
    }

    public void clickSendButton() {
        sleep(2);
        sendButton.click();
    }

    public boolean isUsernameDoesNotExistMessageDisplayed() {
        return TestUtils.isVisible(driver, usernameNotExistMessage);
    }


    public boolean isForgotPasswordVisible() {
        return TestUtils.isVisible(driver, forgotPasswordLink_1);
    }

    public void clickInstagramIcon() {
        sleep(3);
        instagramIcon.click();
    }
}
