package myCredoPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class RegistrationPage {
    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By
            personalNumberInputField = By.xpath("//input[@id='personalNumber']"),
            nextButton = By.xpath("//button[@type='submit']"),
            datesDropdown = By.xpath("//crd-new-select-option//div[@role='listitem']"),
            dateDropdownHeaders = By.xpath("//crd-date-dropdowns//crd-new-select//h6"),
            registrationFormHeader = By.xpath("//app-registration//h5"),
            errorTextFieldBelowDatesField = By.xpath("//crd-date-dropdowns/following-sibling::p"),
            errorTextFieldBelowPersonalNumberInputField = By.xpath("//identity-verification-form[@controlkey='personalInfo']//crd-error");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    @Step("Click on date dropdown with label: {labelText}")
    public RegistrationPage clickDateDropdownByText(String labelText) {
        driver.findElements(dateDropdownHeaders)
                .stream()
                .filter(el -> el.getText().trim().equalsIgnoreCase(labelText))
                .findFirst()
                .ifPresent(WebElement::click);

        return this;
    }

    @Step("fill personal number input field with value: {personalNumber}")
    public RegistrationPage inputPersonalNumber(String personalNumber) {
        driver.findElement(personalNumberInputField).sendKeys(personalNumber);
        return this;
    }

    @Step("Get error message below dates field")
    public String getErrorTextBelowDatesField() {
        waitUtils.waitForElementToBeVisible(errorTextFieldBelowDatesField);
        return driver.findElement(errorTextFieldBelowDatesField).getText();
    }

    @Step("Get error message below personal number field")
    public String getErrorTextBelowPersonalNumberField() {
        waitUtils.waitForElementToBeVisible(errorTextFieldBelowPersonalNumberInputField);
        return driver.findElement(errorTextFieldBelowPersonalNumberInputField).getText();
    }

    @Step("Click to next button")
    public RegistrationPage clickToNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }

    @Step("Wait until personal number field is visible on registration page")
    public RegistrationPage waitUntilPersonalNumberFieldIsVisibleOnRegistrationPage() {
        waitUtils.waitForElementToBeVisible(personalNumberInputField);
        return this;
    }

    @Step("Choose first date in dropdown field")
    public RegistrationPage chooseDateInDropDown() {
        waitUtils.waitForElementToBeVisible(datesDropdown);
        driver.findElements(datesDropdown).getFirst().click();
        return this;
    }

    @Step("check header text language is equal to: {headerName}")
    public RegistrationPage checkHeaderTextLanguage(String headerName) {
        waitUtils.waitForElementToHaveText(registrationFormHeader, headerName);
        return this;
    }
}
