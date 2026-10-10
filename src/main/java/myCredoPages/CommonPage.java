package myCredoPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class CommonPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By
            chooseLanguageButton = By.xpath("//app-icon[@svgicon ='language']//parent::button"),
            errorAlert = By.xpath("//div[@role='alert']//p");

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Click to choose language button")
    public CommonPage clickToChooseLanguageButton() {
        driver.findElement(chooseLanguageButton).click();
        return this;
    }

    @Step("wait until error message is visible")
    public CommonPage waitForErrorToBeVisible() {
        waitUtils.waitForElementToBeVisible(errorAlert);
        return this;
    }

    @Step("wait until page is loaded")
    public CommonPage waitUntilPageIsLoaded() {
        waitUtils.waitForPageLoad();
        return this;
    }

    @Step("wait until switch language button is visible")
    public CommonPage waitForLanguageSwitchButtonToBeVisible() {
        waitUtils.waitForElementToBeVisible(chooseLanguageButton);
        return this;
    }

    @Step("Get error alert message text")
    public String getErrorAlertTextFieldText() {
        return driver.findElement(errorAlert).getText();
    }

}

