package myCredoNegativeTestCases;

import config.baseTest.BaseTest;
import data.dataProvider.LanguagesDataProvider;
import data.enums.LanguageData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import myCredoPages.AuthorizationPage;
import myCredoPages.LanguageSelector;
import myCredoPages.RegistrationPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.driver.DriverManager.getDriver;
import static data.constants.Constants.LANGUAGES_DATA;
import static utils.RandomStringGenerator.generateRandomString;

@Epic("MyCredo internet banking")
@Feature("Registration module")
public class RegistrationNegativeTestCases extends BaseTest {

    private RegistrationPage registrationPage;
    private AuthorizationPage authorizationPage;
    private LanguageSelector languageSelector;

    @BeforeMethod
    public void chooseLanguageSetUp() {
        registrationPage = new RegistrationPage(getDriver());
        authorizationPage = new AuthorizationPage(getDriver());
        languageSelector = new LanguageSelector(getDriver());

        commonPage
                .waitUntilPageIsLoaded()
                .waitForLanguageSwitchButtonToBeVisible()
                .clickToChooseLanguageButton();
    }

    @Test(priority = 1, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("validate error message when personal number field is empty")
    public void checkRegistrationFeatureWithEmptyPersonalNumberAndEmptyDates(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());
        authorizationPage.waitUntilElementIsClickableAndClickToRegistrationButton();

        registrationPage
                .checkHeaderTextLanguage(languageData.getRegistrationHeaderText())
                .waitUntilPersonalNumberFieldIsVisibleOnRegistrationPage()
                .clickToNextButton();

        softAssert.assertEquals(registrationPage.getErrorTextBelowDatesField(), languageData.getRequiredFieldsError());
        softAssert.assertEquals(registrationPage.getErrorTextBelowPersonalNumberField(), languageData.getRequiredFieldsError());
    }

    @Test(priority = 2, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("validate error message after entering incorrect personal number length")
    public void checkRegistrationFeatureWithInvalidPersonalNumber(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());
        authorizationPage.waitUntilElementIsClickableAndClickToRegistrationButton();

        registrationPage
                .checkHeaderTextLanguage(languageData.getRegistrationHeaderText())
                .waitUntilPersonalNumberFieldIsVisibleOnRegistrationPage()
                .inputPersonalNumber(generateRandomString(10))
                .clickToNextButton();

        //პირადი ნომრის შესავსებ ველზე არ დევს მანდატორობა ციფრებზე და ამიტომ ვერ ვამოწმებ
        softAssert.assertEquals(registrationPage.getErrorTextBelowPersonalNumberField(), languageData.getWrongLenghtPersonalNumberFieldError());
    }

    @Test(priority = 3, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("check error message when date fields are incomplete and personal number is filled")
    public void checkRegistrationFeatureWithIncompleteDateButFilledPersonalNumber(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());
        authorizationPage.waitUntilElementIsClickableAndClickToRegistrationButton();

        registrationPage
                .checkHeaderTextLanguage(languageData.getRegistrationHeaderText())
                .waitUntilPersonalNumberFieldIsVisibleOnRegistrationPage()
                .inputPersonalNumber(generateRandomString(11))
                .clickDateDropdownByText(languageData.getDay())
                .chooseDateInDropDown()
                .clickToNextButton();

        softAssert.assertEquals(registrationPage.getErrorTextBelowDatesField(), languageData.getRequiredFieldsError());
    }

    @Test(priority = 4, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("check error alert is visible when registering an underage user and check error text")
    public void checkRegistrationWithUnderageUser(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());
        authorizationPage.waitUntilElementIsClickableAndClickToRegistrationButton();

        registrationPage
                .checkHeaderTextLanguage(languageData.getRegistrationHeaderText())
                .waitUntilPersonalNumberFieldIsVisibleOnRegistrationPage()
                .inputPersonalNumber(generateRandomString(11))
                .clickDateDropdownByText(languageData.getDay())
                .chooseDateInDropDown()
                .clickDateDropdownByText(languageData.getMonth())
                .chooseDateInDropDown()
                .clickDateDropdownByText(languageData.getYear())
                .chooseDateInDropDown()
                .clickToNextButton();

        commonPage.waitForErrorToBeVisible();
        softAssert.assertEquals(commonPage.getErrorAlertTextFieldText(), languageData.getUnderagePersonError());
    }
}
