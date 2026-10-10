package myCredoNegativeTestCases;

import config.baseTest.BaseTest;
import data.dataProvider.LanguagesDataProvider;
import data.enums.LanguageData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import myCredoPages.AuthorizationPage;
import myCredoPages.LanguageSelector;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static config.driver.DriverManager.getDriver;
import static data.constants.Constants.LANGUAGES_DATA;
import static utils.RandomStringGenerator.generateRandomString;

@Epic("MyCredo internet banking")
@Feature("Authorization module")
public class AuthorizationNegativeTestCases extends BaseTest {

    private AuthorizationPage authorizationPage;
    private LanguageSelector languageSelector;

    @BeforeMethod
    public void chooseLanguageSetUp() {
        authorizationPage = new AuthorizationPage(getDriver());
        languageSelector = new LanguageSelector(getDriver());

        commonPage
                .waitUntilPageIsLoaded()
                .waitForLanguageSwitchButtonToBeVisible()
                .clickToChooseLanguageButton();
    }

    @Test(priority = 1, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("validate error messages when submitting empty username and password field")
    public void checkLogInFeatureWithEmptyFields(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());

        authorizationPage
                .checkHeaderTextLanguage(languageData.getLogInHeaderText())
                .clickToLogInButton();

        softAssert.assertEquals(authorizationPage.getErrorTextBelowPasswordField(), languageData.getRequiredFieldsError());
        softAssert.assertEquals(authorizationPage.getErrorTextBelowUsernameField(), languageData.getRequiredFieldsError());
    }

    @Test(priority = 2, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("validate error when password field is left empty and username is filled")
    public void checkLogInFeatureWithOnlyEmptyPassword(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());

        authorizationPage
                .checkHeaderTextLanguage(languageData.getLogInHeaderText())
                .enterUsername(generateRandomString(8))
                .clickToLogInButton();

        softAssert.assertEquals(authorizationPage.getErrorTextBelowPasswordField(), languageData.getRequiredFieldsError());
    }

    @Test(priority = 3, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("validate error when username field is empty and password is filled")
    public void checkLogInFeatureWithOnlyEmptyUsername(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());

        authorizationPage
                .checkHeaderTextLanguage(languageData.getLogInHeaderText())
                .enterPassword(generateRandomString(8))
                .clickToLogInButton();

        softAssert.assertEquals(authorizationPage.getErrorTextBelowUsernameField(), languageData.getRequiredFieldsError());
    }

    @Test(priority = 4, dataProvider = LANGUAGES_DATA, dataProviderClass = LanguagesDataProvider.class)
    @Description("check error alert when logging in with invalid credentials")
    public void checkLogInFeatureWithInvalidCredentials(LanguageData languageData) {
        languageSelector.checkLanguagesAreVisibleInDropDownAndChooseLanguage(languageData.getNameOfLanguage());

        authorizationPage
                .checkHeaderTextLanguage(languageData.getLogInHeaderText())
                .enterUsername(generateRandomString(8))
                .enterPassword(generateRandomString(7))
                .clickToLogInButton();

        softAssert.assertEquals(commonPage.waitForErrorAndGetAlertTextFieldText(), languageData.getInvalidCredentialsError());
    }
}
