package config.baseTest;

import config.driver.DriverManager;
import data.constants.Constants;
import myCredoPages.CommonPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static config.driver.DriverManager.getDriver;

public class BaseTest {

    protected CommonPage commonPage;
    protected SoftAssert softAssert;

    @BeforeMethod
    public void aSetUp() {

        DriverManager.createDriver();
        getDriver().get(Constants.BASE_URL);

        softAssert = new SoftAssert();
        commonPage = new CommonPage(getDriver());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        try {
            if (softAssert != null) {
                softAssert.assertAll();
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}
