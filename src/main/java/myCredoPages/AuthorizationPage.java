package myCredoPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class AuthorizationPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final String
            userNameInputXpath = "//input[@id = 'username']",
            passwordInputXpath = "//input[@id = 'password']",
            errorTextFieldBelowInputFieldXpath = "/parent::div/following-sibling::div//crd-error";
    //ამ ელემენტებზე დუბლიკაციები მოგვიწევდა პირდაპირ xpath-ებში რომ გაგვეტანა და სტრინგში ამიტომ ვინახავ

    private final By
            usernameInputField = By.xpath(userNameInputXpath),
            passwordInput = By.xpath(passwordInputXpath),
            loginButton = By.xpath("//button[@aria-label='sign in']"),
            registrationButton = By.xpath("//a[@role='link' and @aria-label='registration']"),
            logInFormHeader = By.xpath("//app-authorization//h5"),
            errorFieldBelowUserNameInputField = By.xpath(userNameInputXpath + errorTextFieldBelowInputFieldXpath),
            errorFieldBelowPasswordInputField = By.xpath(passwordInputXpath + errorTextFieldBelowInputFieldXpath);

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Enter value: {username} to username field")
    public AuthorizationPage enterUsername(String username) {
        driver.findElement(usernameInputField).sendKeys(username);
        return this;
    }

    @Step("Enter value: {password} to password field")
    public AuthorizationPage enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Click to Login button")
    public AuthorizationPage clickToLogInButton() {
        driver.findElement(loginButton).click();
        return this;
    }
    @Step("Get error message below username input field")
    public String getErrorTextBelowUsernameField() {
        waitUtils.waitForElementToBeVisible(errorFieldBelowUserNameInputField);
        return driver.findElement(errorFieldBelowUserNameInputField).getText();
    }

    @Step("Get error message below password input field")
    public String getErrorTextBelowPasswordField() {
        waitUtils.waitForElementToBeVisible(errorFieldBelowPasswordInputField);
        return driver.findElement(errorFieldBelowPasswordInputField).getText();
    }

    @Step("Click to Registration button")
    public AuthorizationPage waitUntilElementIsClickableAndClickToRegistrationButton() {
        WebElement registrationButtonWebElement = driver.findElement(registrationButton);
        waitUtils.waitForElementToBeClickable(registrationButtonWebElement);
        registrationButtonWebElement.click();
        return this;
    }

    @Step("Check header is present in correct language and is equal to {headerText}")
    public AuthorizationPage checkHeaderTextLanguage(String headerText) {
        waitUtils.waitForElementToHaveText(logInFormHeader, headerText);
        return this;
    }


}

