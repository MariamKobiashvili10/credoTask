package data.dataProvider;

import data.constants.Constants;
import data.enums.LanguageData;
import org.testng.annotations.DataProvider;

public class LanguagesDataProvider {


    @DataProvider(name = Constants.LANGUAGES_DATA)
    public Object[][] languages() {
        return new Object[][]{
                {LanguageData.GEORGIAN},
                {LanguageData.ENGLISH}
        };
    }
}
