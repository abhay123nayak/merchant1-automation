package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.TestUtils;

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

    @FindBy(xpath = "//input[@id='fullName']")
    private WebElement forgotUsernameInput;

    @FindBy(xpath = "//button[contains(text(),'Send')]")
    private WebElement sendButton;

    @FindBy(xpath = "//div[contains(text(),'Username not exists')]")
    private WebElement usernameNotExistMessage;

    @FindBy(xpath = "//i[contains(@class, 'fa-instagram')]")
    private WebElement instagramIcon;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForLoginElements() {
        TestUtils.isVisible(driver, usernameInput);
        TestUtils.isVisible(driver, passwordInput);
        TestUtils.isVisible(driver, loginButton);
    }

    public void enterUsername(String username) {
        TestUtils.waitForVisibility(driver, usernameInput).clear();
        TestUtils.waitForVisibility(driver, usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        TestUtils.waitForVisibility(driver, passwordInput).clear();
        TestUtils.waitForVisibility(driver, passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        TestUtils.waitForElementToBeClickable(driver, loginButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return TestUtils.isVisible(driver, errorMessageToaster);
    }

    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public void clickForgotPassword() {
        TestUtils.waitForElementToBeClickable(driver, forgotPasswordLink).click();
    }

    public void enterForgotUsername(String username) {
        TestUtils.waitForVisibility(driver, forgotUsernameInput).clear();
        TestUtils.waitForVisibility(driver, forgotUsernameInput).sendKeys(username);
    }

    public void clickSendButton() {
        TestUtils.waitForElementToBeClickable(driver, sendButton).click();
    }

    public boolean isUsernameDoesNotExistMessageDisplayed() {
        return TestUtils.isVisible(driver, usernameNotExistMessage);
    }

    public boolean isForgotPasswordVisible() {
        return TestUtils.isVisible(driver, forgotPasswordLink_1);
    }

    public void clickInstagramIcon() {
        TestUtils.waitForVisibility(driver, instagramIcon);
        TestUtils.waitForElementToBeClickable(driver, instagramIcon).click();
    }
}
