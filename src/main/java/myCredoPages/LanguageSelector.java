package myCredoPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LanguageSelector {
    private final WaitUtils waitUtils;

    private final WebDriver driver;

    public LanguageSelector(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    private By languageOption(String language) {
        return By.xpath("//div[contains(@class,'cdk-overlay-pane')]//li[normalize-space()='"+language+"']");
    }

    @Step("checking languages are visible in dropdown menu and choosing language {language}")
    public LanguageSelector checkLanguagesAreVisibleInDropDownAndChooseLanguage(String language) {
        By languageOption = languageOption(language);
        waitUtils.waitForElementToBeVisible(languageOption);
        driver.findElement(languageOption).click();
        return this;
    }
}

