package utils;

import config.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class WaitUtils {
    private final WebDriverWait wait;


    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(12)
        );
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForPageLoad() {
        wait.until(driver ->
                Objects.equals(
                        ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState"),
                        "complete"
                )
        );
    }

    public void waitForElementToHaveText(By locator, String text) {
        wait.until(ExpectedConditions.textToBe(locator, text));
    }
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
